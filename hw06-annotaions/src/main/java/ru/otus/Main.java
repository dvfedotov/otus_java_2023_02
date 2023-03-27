package ru.otus;

import ru.otus.homework.test.TestClass;
import ru.otus.homework.test.TestClass2;
import ru.otus.homework.test.TestClass3;

import static ru.otus.homework.testrunner.TestRunner.runTests;

public class Main {
    public static void main(String[] args) {

        runTests(TestClass.class);
        runTests(TestClass2.class);
        runTests(TestClass3.class);
    }
}