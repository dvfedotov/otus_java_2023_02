package ru.otus.homework.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.exception.AtmException;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmService;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class AtmServiceImpl implements AtmService {

    public static final String ERROR_MESSAGE_NO_CASH = "Failed to receive the amount because there is no cash in the ATM";
    public static final String ERROR_MESSAGE_NOT_ENOUGH_CASH = "Failed to receive the amount because there is not enough cash in the ATM";
    public static final String ERROR_MESSAGE_NO_BANKNOTES = "Failed to receive the amount because there are no necessary banknotes in the ATM";
    public static final String ERROR_MESSAGE_NO_CELL = "Unable to add currency because required cell is missing";


    @Override
    public void addCurrency(@NonNull Atm atm, Currency currency, int count) {
        Optional<Cell> optionalCell = findCell(atm, currency);
        optionalCell.ifPresentOrElse(c -> c.addCash(count),
                () -> {
                    throw new AtmException(ERROR_MESSAGE_NO_CELL);
                });
    }


    @Override
    public Map<Currency, Integer> getCash(@NonNull Atm atm, int sum) {
        int balance = atm.getBalance();
        if (balance <= 0) {
            throw new AtmException(ERROR_MESSAGE_NO_CASH);
        }
        if (sum > balance) {
            throw new AtmException(ERROR_MESSAGE_NOT_ENOUGH_CASH);
        }
        Map<Currency, Integer> cashMap = getCashMap(atm, sum);
        updateCellList(atm, cashMap);
        return cashMap;
    }

    private Map<Currency, Integer> getCashMap(@NonNull Atm atm, int sum) {
        Map<Currency, Integer> cashMap = new EnumMap<>(Currency.class);
        for (Cell cell : atm.getCells()) {
            int currency = cell.getCurrency().getValue();
            int count = sum / currency;
            if (cell.getCount() > 0 && count > 0) {
                if (cell.getCount() >= count) {
                    cashMap.put(cell.getCurrency(), count);
                    sum = sum - currency * count;
                } else {
                    cashMap.put(cell.getCurrency(), cell.getCount());
                    sum = sum - cell.getCount() * currency;
                }
            }
        }
        if (sum > 0) {
            throw new AtmException(ERROR_MESSAGE_NO_BANKNOTES);
        }
        return cashMap;
    }

    private void updateCellList(@NonNull Atm atm, Map<Currency, Integer> cashMap) {
        for (Map.Entry<Currency, Integer> entry : cashMap.entrySet()) {
            Optional<Cell> optionalCell = findCell(atm, entry.getKey());
            optionalCell.ifPresentOrElse(c -> c.getCash(entry.getValue()),
                    () -> {
                        throw new AtmException(ERROR_MESSAGE_NO_CELL);
                    });
        }
    }

    private Optional<Cell> findCell(@NonNull Atm atm, Currency currency) {
        return atm.getCells().stream().filter(c -> c.getCurrency().equals(currency)).findFirst();
    }
}
