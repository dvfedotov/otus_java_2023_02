package ru.otus.homework.test;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

@Slf4j
public class TestClass5 {

    private int count;

    @After
    public void globalTearDown() {
        count = 0;
        log.info("tear down @After count [{}]", count);
        log.info("*************  @After ****************");
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

}
