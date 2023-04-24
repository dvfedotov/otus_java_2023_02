package ru.otus.homework.model;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import ru.otus.homework.exception.AtmException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Getter
@ToString
public class Atm {

    public static final String ERROR_MESSAGE_NO_CASH = "Failed to receive the amount because there is no cash in the ATM";
    public static final String ERROR_MESSAGE_NOT_ENOUGH_CASH = "Failed to receive the amount because there is not enough cash in the ATM";
    public static final String ERROR_MESSAGE_NO_BANKNOTES = "Failed to receive the amount because there are no necessary banknotes in the ATM";
    private List<Cell> cellList;

    public void addCurrency(Currency currency, int count) {
        if (CollectionUtils.isEmpty(cellList)) {
            cellList = new ArrayList<>();
        }
        Optional<Cell> optionalCell = findCell(currency);
        if (optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            cell.setCount(cell.getCount() + count);
        } else {
            cellList.add(new Cell(currency, count));
        }
    }

    public int getBalance() {
        if (CollectionUtils.isEmpty(cellList)) {
            return 0;
        }
        return cellList.stream()
                .mapToInt(c -> c.getCurrency().getValue() * c.getCount())
                .reduce(0, Integer::sum);
    }

    public Map<Currency, Integer> getCash(int sum) {
        if (CollectionUtils.isEmpty(cellList)) {
            throw new AtmException(ERROR_MESSAGE_NO_CASH);
        }
        if (sum > getBalance()) {
            throw new AtmException(ERROR_MESSAGE_NOT_ENOUGH_CASH);
        }
        reverseSortCellList();
        Map<Currency, Integer> cashMap = getCashMap(sum);
        updateCellList(cashMap);
        return cashMap;
    }

    private void reverseSortCellList() {
        cellList.sort(Comparator.comparingInt(c -> c.getCurrency().getValue()));
        Collections.reverse(cellList);
    }

    private Map<Currency, Integer> getCashMap(int sum) {
        Map<Currency, Integer> cashMap = new EnumMap<>(Currency.class);
        for (Cell cell : cellList) {
            int currency = cell.getCurrency().getValue();
            int count = sum / currency;
            if (count > 0) {
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

    private void updateCellList(Map<Currency, Integer> cashMap) {
        for (Map.Entry<Currency, Integer> entry : cashMap.entrySet()) {
            Optional<Cell> optionalCell = findCell(entry.getKey());
            optionalCell.ifPresent(cell -> {
                int count = cell.getCount() - entry.getValue();
                if (count <= 0) {
                    cellList.remove(cell);
                } else {
                    cell.setCount(count);
                }
            });
        }
    }

    private Optional<Cell> findCell(Currency currency) {
        return cellList.stream().filter(c -> c.getCurrency().equals(currency)).findFirst();
    }
}
