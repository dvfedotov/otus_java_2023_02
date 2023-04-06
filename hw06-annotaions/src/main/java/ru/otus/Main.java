package ru.otus;

import ru.otus.homework.test.TestClass;
import ru.otus.homework.test.TestClass2;
import ru.otus.homework.test.TestClass3;
import ru.otus.homework.test.TestClass4;
import ru.otus.homework.test.TestClass5;
import ru.otus.homework.testrunner.TestRunner;


public class Main {
    public static void main(String[] args) {
        TestRunner runner = new TestRunner();

        runner.runTests(TestClass.class);
        runner.runTests(TestClass2.class);
        runner.runTests(TestClass3.class);
        runner.runTests(TestClass4.class);
        runner.runTests(TestClass5.class);
    }
}