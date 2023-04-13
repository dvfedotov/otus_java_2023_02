package ru.otus.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.service.SuperCalculator;
import ru.otus.homework.annotations.Log;

@Slf4j
public class SuperCalculatorImpl implements SuperCalculator {
    @Log
    @Override
    public void calculation(int param1) {
        log.info("calculation [{}]", param1);
    }


    @Override
    public void calculation(int param1, int param2) {
        log.info("calculation [{}, {}]", param1, param2);
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        log.info("calculation [{}, {}, {}]", param1, param2, param3);
    }

}
