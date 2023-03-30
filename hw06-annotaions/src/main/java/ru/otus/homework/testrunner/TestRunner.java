package ru.otus.homework.testrunner;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.model.AnnotationsTestMethods;
import ru.otus.homework.model.TestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestRunner {

    public static final String ERROR_MESSAGE = "Error when validate {}";

    public static void runTests(Class<?> clazz) {
        TestResult result = new TestResult();
        var testMethods = getMethods(clazz, Test.class);
        Method beforeMethod;
        try {
            beforeMethod = getMethod(clazz, Before.class);
        } catch (UnsupportedOperationException ex) {
            log.error(ERROR_MESSAGE, clazz.getSimpleName(), ex);
            return;
        }
        Method afterMethod;
        try {
            afterMethod = getMethod(clazz, After.class);
        } catch (UnsupportedOperationException ex) {
            log.error(ERROR_MESSAGE, clazz.getSimpleName(), ex);
            return;
        }
        testMethods.forEach(m -> {
            AnnotationsTestMethods annotationsTestMethods = AnnotationsTestMethods.builder()
                    .beforeMethod(beforeMethod)
                    .afterMethod(afterMethod)
                    .testMethod(m).build();
            invokeTest(clazz, result, annotationsTestMethods);
        });
        log.info("*************  Tests in {} complete ****************", clazz.getSimpleName());
        log.info("Tests run: [{}], Pass: [{}], Failures: [{}]",
                result.getCountPass() + result.getCountFall(), result.getCountPass(), result.getCountFall());
        log.info("****************************************************");
    }

    private static Method getMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        var methods = getMethods(clazz, annotation);
        if (methods.size() > 1) {
            throw new UnsupportedOperationException("Should be only one annotation " + annotation.getSimpleName());
        }
        return methods.isEmpty() ? null : methods.get(0);
    }

    private static List<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(annotation))
                .toList();
    }

    private static void invokeTest(Class<?> clazz, TestResult result, AnnotationsTestMethods annotationsTestMethods) {
        Method method = annotationsTestMethods.getTestMethod();
        try {
            Object testClass = clazz.getDeclaredConstructor().newInstance();
            if (annotationsTestMethods.getBeforeMethod() != null) {
                annotationsTestMethods.getBeforeMethod().invoke(testClass);
            }
            method.invoke(testClass);
            if (annotationsTestMethods.getAfterMethod() != null) {
                annotationsTestMethods.getAfterMethod().invoke(testClass);
            }
            log.info("{} pass test", method.getName());
            result.testPass();
        } catch (Exception ex) {
            log.info("{} fall test", method.getName(), ex);
            result.testFall();
        }
    }


}