package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AnnotationDemo {

    public static void main(String[] args) {
        Map<Boolean, Integer> result = new HashMap<>();
        result.put(true, 0);
        result.put(false, 0);
        TestClass anyClass = new TestClass();
        for (Method method : anyClass.getClass().getDeclaredMethods()) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Test) {
                    TestClass testClass = new TestClass();
                    invokeBefore(testClass);
                    try {
                        method.invoke(testClass);
                        log.info("{} pass test", method.getName());
                        result.put(true, result.get(true) + 1);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        log.info("{} fall test", method.getName(), ex);
                        result.put(false, result.get(false) + 1);
                    }
                    invokeAfter(testClass);
                }
            }
        }
        log.info("Tests run: [{}], Pass: [{}], Failures: [{}]", result.get(true) + result.get(false), result.get(true), result.get(false));
    }

    private static void invokeAfter(TestClass testClass) {
        for (Method afterMethod : testClass.getClass().getDeclaredMethods()) {
            Annotation[] afterAnnotations = afterMethod.getAnnotations();
            for (Annotation afterAannotation : afterAnnotations) {
                if (afterAannotation instanceof After) {
                    try {
                        afterMethod.invoke(testClass);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        log.info("fall After", ex);
                    }
                }
            }
        }
    }

    private static void invokeBefore(TestClass testClass) {
        for (Method beforeMethod : testClass.getClass().getDeclaredMethods()) {
            Annotation[] beforeAnnotations = beforeMethod.getAnnotations();
            for (Annotation beforeAannotation : beforeAnnotations) {
                if (beforeAannotation instanceof Before) {
                    try {
                        beforeMethod.invoke(testClass);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        log.info("fall Before", ex);
                    }
                }
            }
        }
    }
}