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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmServiceImplTest {

    public static final String ERROR_MESSAGE_NO_CASH = "Failed to receive the amount because there is no cash in the ATM";
    public static final String ERROR_MESSAGE_NOT_ENOUGH_CASH = "Failed to receive the amount because there is not enough cash in the ATM";
    public static final String ERROR_MESSAGE_NO_BANKNOTES = "Failed to receive the amount because there are no necessary banknotes in the ATM";
    public static final String ERROR_MESSAGE_NO_CELL = "Unable to add currency because required cell is missing";
    private static AtmService service;

    @BeforeAll
    static void initAll() {
        service = new AtmServiceImpl();

    }

    @Test
    void getBalance_success() {
        Atm atm = fillAtm();

        int actual = service.getBalance(atm);

        assertEquals(66000, actual);
    }

    @Test
    void getCash_success() {
        Atm atm = fillAtm();

        Map<Currency, Integer> actual = service.getCash(atm, 66000);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(10, actual.get(Currency.ONE_HUNDRED)),
                () -> assertEquals(10, actual.get(Currency.FIVE_HUNDRED)),
                () -> assertEquals(10, actual.get(Currency.ONE_THOUSAND)),
                () -> assertEquals(10, actual.get(Currency.FIVE_THOUSAND)));
    }

    @Test
    void getCash_whenNoCash_throwException() {
        Atm atm = createAtm();

        AtmException exception = assertThrows(AtmException.class, () -> service.getCash(atm, 66000));

        assertEquals(ERROR_MESSAGE_NO_CASH, exception.getMessage());
    }

    @Test
    void getCash_whenNotEnoughCash_throwException() {
        Atm atm = fillAtm();

        AtmException exception = assertThrows(AtmException.class, () -> service.getCash(atm, 100000));

        assertEquals(ERROR_MESSAGE_NOT_ENOUGH_CASH, exception.getMessage());
    }

    @Test
    void getCash_whenNoBanknotes_throwException() {
        Atm atm = fillAtm();

        AtmException exception = assertThrows(AtmException.class, () -> service.getCash(atm, 6782));

        assertEquals(ERROR_MESSAGE_NO_BANKNOTES, exception.getMessage());
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