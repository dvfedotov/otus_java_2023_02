package ru.otus.homework.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Cell implements Comparable<Cell> {

    private final Currency currency;
    private int count;

    public Cell(Currency currency) {
        this.currency = currency;
    }

    public void addCash(int count) {
        if (count > 0) {
            this.count += count;
        }
    }

    public void getCash(int count) {
        if (count >= this.count) {
            this.count -= count;
        }
    }

    @Override
    public int compareTo(Cell c) {
        return Integer.compare(this.getCurrency().getValue(), c.getCurrency().getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;

        return getCurrency() == cell.getCurrency();
    }

    @Override
    public int hashCode() {
        return getCurrency().hashCode();
    }
}
