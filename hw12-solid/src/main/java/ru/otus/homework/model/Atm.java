package ru.otus.homework.model;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@ToString
public class Atm {

    private final Set<Cell> cells;

    public Atm(List<Cell> cellList) {
        NavigableSet<Cell> treeSet = new TreeSet<>(Comparator.comparingInt(c -> c.getCurrency().getValue()));
        treeSet.addAll(cellList);
        cells = treeSet.descendingSet();
    }

    public Set<Cell> getCells() {
        return Collections.unmodifiableSet(cells);
    }

    public int getBalance() {
        if (CollectionUtils.isEmpty(cells)) {
            return 0;
        }
        return cells.stream()
                .mapToInt(c -> c.getCurrency().getValue() * c.getCount())
                .reduce(0, Integer::sum);
    }

    public Optional<Cell> findCell(Currency currency) {
        return getCells().stream().filter(c -> c.getCurrency().equals(currency)).findFirst();
    }
}
