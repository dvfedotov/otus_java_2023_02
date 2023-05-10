package ru.otus.homework.model;

public enum Currency {
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    private final int value;

    Currency(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name().replace("_", " ");
    }
}
