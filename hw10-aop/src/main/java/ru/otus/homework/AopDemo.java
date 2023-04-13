package ru.otus.homework;


import ru.otus.homework.aop.Ioc;
import ru.otus.homework.service.SuperCalculator;

public class AopDemo {
    public static void main(String[] args) {
        SuperCalculator superCalculator = Ioc.createProxyLogClass();
        superCalculator.calculation(1);
        superCalculator.calculation(1, 2);
        superCalculator.calculation(1, 2, "param3");
    }
}



