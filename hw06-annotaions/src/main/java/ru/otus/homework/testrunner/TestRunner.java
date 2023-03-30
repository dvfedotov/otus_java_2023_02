package ru.otus.homework.testrunner;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.model.TestResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class TestRunner {

    public static void runTests(Class<?> clazz) {
        TestResult result = new TestResult();
        var testMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .toList();
        testMethods.forEach(m -> invokeTest(result, m, clazz));
        log.info("*************  Test runner complete ****************");
        log.info("Tests run: [{}], Pass: [{}], Failures: [{}]",
                result.getCountPass() + result.getCountFall(), result.getCountPass(), result.getCountFall());
        log.info("****************************************************");
    }

    private static void invokeTest(TestResult result, Method m, Class<?> clazz) {
        try {
            Object testClass = clazz.getDeclaredConstructor().newInstance();
            invokeBefore(testClass);
            m.invoke(testClass);
            invokeAfter(testClass);
            log.info("{} pass test", m.getName());
            result.testPass();
        } catch (Exception ex) {
            log.info("{} fall test", m.getName(), ex);
            result.testFall();
        }
    }

    private static void invokeBefore(Object testClass) {
        var methods = Arrays.stream(testClass.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Before.class)).toList();
        if (methods.size() > 1) {
            throw new UnsupportedOperationException("Should be only one @Before");
        }
        try {
            if (!methods.isEmpty()) {
                methods.get(0).invoke(testClass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.info("fall Before", ex);
        }
    }

    private static void invokeAfter(Object testClass) {
        var methods = Arrays.stream(testClass.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(After.class)).toList();
        if (methods.size() > 1) {
            throw new UnsupportedOperationException("Should be only one @After");
        }
        try {
            if (!methods.isEmpty()) {
                methods.get(0).invoke(testClass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.info("fall After", ex);
        }
    }
}