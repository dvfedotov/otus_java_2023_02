package ru.otus.homework.model;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import ru.otus.homework.exception.AtmException;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Getter
@ToString
public class Atm {

    public static final String ERROR_MESSAGE_NO_CASH = "Failed to receive the amount because there is no cash in the ATM";
    public static final String ERROR_MESSAGE_NOT_ENOUGH_CASH = "Failed to receive the amount because there is not enough cash in the ATM";
    public static final String ERROR_MESSAGE_NO_BANKNOTES = "Failed to receive the amount because there are no necessary banknotes in the ATM";
    private final Set<Cell> cells;

    public Atm() {
        NavigableSet<Cell> treeSet = new TreeSet<>(Comparator.comparingInt(c -> c.getCurrency().getValue()));
        treeSet.add(new Cell(Currency.ONE_HUNDRED));
        treeSet.add(new Cell(Currency.FIVE_HUNDRED));
        treeSet.add(new Cell(Currency.ONE_THOUSAND));
        treeSet.add(new Cell(Currency.FIVE_THOUSAND));
        cells = treeSet.descendingSet();
    }

    public void addCurrency(Currency currency, int count) {
        Optional<Cell> optionalCell = findCell(currency);
        Cell cell = optionalCell.get();
        cell.addCash(count);
    }

    public int getBalance() {
        if (CollectionUtils.isEmpty(cells)) {
            return 0;
        }
        return cells.stream()
                .mapToInt(c -> c.getCurrency().getValue() * c.getCount())
                .reduce(0, Integer::sum);
    }

    public Map<Currency, Integer> getCash(int sum) {
        int balance = getBalance();
        if (balance <= 0) {
            throw new AtmException(ERROR_MESSAGE_NO_CASH);
        }
        if (sum > balance) {
            throw new AtmException(ERROR_MESSAGE_NOT_ENOUGH_CASH);
        }
        Map<Currency, Integer> cashMap = getCashMap(sum);
        updateCellList(cashMap);
        return cashMap;
    }

    private Map<Currency, Integer> getCashMap(int sum) {
        Map<Currency, Integer> cashMap = new EnumMap<>(Currency.class);
        for (Cell cell : cells) {
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

    private void updateCellList(Map<Currency, Integer> cashMap) {
        for (Map.Entry<Currency, Integer> entry : cashMap.entrySet()) {
            Optional<Cell> optionalCell = findCell(entry.getKey());
            optionalCell.ifPresent(c -> c.getCash(entry.getValue()));
        }
    }

    private Optional<Cell> findCell(Currency currency) {
        return this.cells.stream().filter(c -> c.getCurrency().equals(currency)).findFirst();
    }
}
