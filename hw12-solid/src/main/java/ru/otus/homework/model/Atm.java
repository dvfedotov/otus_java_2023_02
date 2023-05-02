package ru.otus.homework.model;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Getter
@ToString
public class Atm {

    private final Set<Cell> cells;

    public Atm() {
        NavigableSet<Cell> treeSet = new TreeSet<>(Comparator.comparingInt(c -> c.getCurrency().getValue()));
        treeSet.add(new Cell(Currency.ONE_HUNDRED));
        treeSet.add(new Cell(Currency.FIVE_HUNDRED));
        treeSet.add(new Cell(Currency.ONE_THOUSAND));
        treeSet.add(new Cell(Currency.FIVE_THOUSAND));
        cells = treeSet.descendingSet();
    }

}
