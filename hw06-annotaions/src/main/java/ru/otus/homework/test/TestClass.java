package ru.otus.homework.test;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

@Slf4j
public class TestClass {

    private int count;

    @Before
    public void globalSetUp() {
        count = 1;
        log.info("init @Before count [{}]", count);
    }

    @After
    public void globalTearDown() {
        count = 0;
        log.info("tear down @After count [{}]", count);
    }
    @Test
    public void testFirst(){
        count++;
        log.info("testFirst @Test count [{}]", count);
    }

    @Test
    public void testSecond(){
        count++;
        log.info("testSecond @Test count [{}]", count);
    }

    @Test
    public void fallTestFirst(){
        count++;
        log.info("fallTest @Test count [{}]", count);
        throw new RuntimeException("fallTest @Test");
    }

    @Test
    public void fallTestSecond(){
        count++;
        log.info("fallTest @Test count [{}]", count);
        throw new RuntimeException("fallTest @Test");
    }


}
