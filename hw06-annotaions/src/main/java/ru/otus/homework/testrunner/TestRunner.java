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
        var beforeMethods = getMethods(clazz, Before.class);
        try {
            validateMethod(beforeMethods, "@Before");
        } catch (UnsupportedOperationException ex) {
            log.error(ERROR_MESSAGE, clazz.getName(), ex);
            return;
        }
        var afterMethods = getMethods(clazz, After.class);
        try {
            validateMethod(afterMethods, "@After");
        } catch (UnsupportedOperationException ex) {
            log.error(ERROR_MESSAGE, clazz.getName(), ex);
            return;
        }
        testMethods.forEach(m -> {
            AnnotationsTestMethods annotationsTestMethods = AnnotationsTestMethods.builder()
                    .beforeMethod(beforeMethods.isEmpty() ? null : beforeMethods.get(0))
                    .afterMethod(afterMethods.isEmpty() ? null : afterMethods.get(0))
                    .testMethod(m).build();
            invokeTest(clazz, result, annotationsTestMethods);
        });
        log.info("*************  Test runner complete ****************");
        log.info("Tests run: [{}], Pass: [{}], Failures: [{}]",
                result.getCountPass() + result.getCountFall(), result.getCountPass(), result.getCountFall());
        log.info("****************************************************");
    }


    private static void validateMethod(List<Method> beforeMethods, String nameAnnotation) {
        if (beforeMethods.size() > 1) {
            throw new UnsupportedOperationException("Should be only one annotation " + nameAnnotation);
        }
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

    private static List<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(annotation))
                .toList();
    }


}