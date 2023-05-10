package ru.otus.homework.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.homework.service.AtmCashService;
import ru.otus.homework.service.impl.AtmCashServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmTest {

    private static AtmCashService service;

    @BeforeAll
    static void initAll() {
        service = new AtmCashServiceImpl();

    }

    @Test
    void getBalance_success() {
        Atm atm = fillAtm();

        int actual = atm.getBalance();

        assertEquals(66000, actual);
    }


    private Atm fillAtm() {
        Atm atm = createAtm();
        service.addCash(atm, Currency.ONE_HUNDRED, 10);
        service.addCash(atm, Currency.FIVE_HUNDRED, 10);
        service.addCash(atm, Currency.ONE_THOUSAND, 10);
        service.addCash(atm, Currency.FIVE_THOUSAND, 10);
        return atm;
    }

    private Atm createAtm() {
        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(Currency.ONE_HUNDRED));
        cellList.add(new Cell(Currency.FIVE_HUNDRED));
        cellList.add(new Cell(Currency.ONE_THOUSAND));
        cellList.add(new Cell(Currency.FIVE_THOUSAND));
        return new Atm(cellList);
    }

}