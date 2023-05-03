package ru.otus.homework.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.homework.service.AtmService;
import ru.otus.homework.service.impl.AtmServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmTest {

    private static AtmService service;

    @BeforeAll
    static void initAll() {
        service = new AtmServiceImpl();

    }

    @Test
    void getBalance_success() {
        Atm atm = fillAtm();

        int actual = atm.getBalance();

        assertEquals(66000, actual);
    }


    private Atm fillAtm() {
        Atm atm = createAtm();
        service.addCurrency(atm, Currency.ONE_HUNDRED, 10);
        service.addCurrency(atm, Currency.FIVE_HUNDRED, 10);
        service.addCurrency(atm, Currency.ONE_THOUSAND, 10);
        service.addCurrency(atm, Currency.FIVE_THOUSAND, 10);
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