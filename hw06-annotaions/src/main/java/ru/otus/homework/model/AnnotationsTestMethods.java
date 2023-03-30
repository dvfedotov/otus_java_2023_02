package ru.otus.homework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
@Builder
public class AnnotationsTestMethods {

    private Method beforeMethod;
    private Method afterMethod;
    private Method testMethod;
}
