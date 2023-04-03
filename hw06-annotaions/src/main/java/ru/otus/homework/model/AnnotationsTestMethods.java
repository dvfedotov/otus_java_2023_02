package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
@AllArgsConstructor
public class AnnotationsTestMethods {

    private Method beforeMethod;
    private Method afterMethod;
    private Method testMethod;
}
