package ru.otus.homework;


import ru.otus.homework.aop.IocLog;

public class AopDemo {
    public static void main(String[] args) {
        LogClass logClass = IocLog.createLogClass();
        logClass.calculation(1);
        logClass.calculation(1, 2);
        logClass.calculation(1, 2, "param3");
    }
}



