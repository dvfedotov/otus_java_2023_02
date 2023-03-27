package ru.otus.homework.testrunner;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestRunner {

    public static void runTests(Class<?> clazz) {
        Map<Boolean, Integer> result = prepareResultMap();
        var testMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .toList();
        testMethods.forEach(m -> invokeTest(result, m, clazz));
        log.info("*************  Test runner complete ****************");
        log.info("Tests run: [{}], Pass: [{}], Failures: [{}]",
                result.get(true) + result.get(false), result.get(true), result.get(false));
        log.info("****************************************************");
    }

    private static Map<Boolean, Integer> prepareResultMap() {
        Map<Boolean, Integer> result = new HashMap<>();
        result.put(true, 0);
        result.put(false, 0);
        return result;
    }

    private static void invokeTest(Map<Boolean, Integer> result, Method m, Class<?> clazz) {
        try {
            Object testClass = clazz.getDeclaredConstructor().newInstance();
            invokeBefore(testClass);
            m.invoke(testClass);
            invokeAfter(testClass);
            log.info("{} pass test", m.getName());
            result.put(true, result.get(true) + 1);
        } catch (Exception ex) {
            log.info("{} fall test", m.getName(), ex);
            result.put(false, result.get(false) + 1);
        }
    }

    private static void invokeAfter(Object testClass) {
        var optionalMethod = Arrays.stream(testClass.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(After.class)).findFirst();
        try {
            if (optionalMethod.isPresent()) {
                optionalMethod.get().invoke(testClass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.info("fall After", ex);
        }
    }


    private static void invokeBefore(Object testClass) {
        var optionalMethod = Arrays.stream(testClass.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Before.class)).findFirst();
        try {
            if (optionalMethod.isPresent()) {
                optionalMethod.get().invoke(testClass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.info("fall Before", ex);
        }
    }
}