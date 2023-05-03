package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.homework.exception.AtmException;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmServiceImplTest {

    public static final String ERROR_MESSAGE_NO_CELL = "Unable to add currency because required cell is missing";
    private static AtmService service;

    @BeforeAll
    static void initAll() {
        service = new AtmServiceImpl();

    }


    @Test
    void addCurrency_whenNoCell_throwException() {
        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(Currency.FIVE_HUNDRED));
        cellList.add(new Cell(Currency.ONE_THOUSAND));
        Atm atm = new Atm(cellList);

        AtmException exception = assertThrows(AtmException.class, () -> service.addCurrency(atm, Currency.ONE_HUNDRED, 100));

        assertEquals(ERROR_MESSAGE_NO_CELL, exception.getMessage());

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