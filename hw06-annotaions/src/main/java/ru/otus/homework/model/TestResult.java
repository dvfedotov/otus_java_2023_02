package ru.otus.homework.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResult {

    private int countPass;
    private int countFall;

    public void testPass() {
        countPass++;
    }

    public void testFall() {
        countFall++;
    }
}
