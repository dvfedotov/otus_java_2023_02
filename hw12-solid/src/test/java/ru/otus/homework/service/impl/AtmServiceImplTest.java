package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.homework.exception.AtmException;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmService;

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
        Atm atm = new Atm();

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
        Atm atm = fillAtm();
        Set<Cell> allCells = atm.getCells();
        Optional<Cell> optionalCell = atm.getCells().stream().filter(c -> c.getCurrency().equals(Currency.ONE_HUNDRED)).findFirst();
        optionalCell.ifPresent(allCells::remove);

        AtmException exception = assertThrows(AtmException.class, () -> service.addCurrency(atm, Currency.ONE_HUNDRED, 100));

        assertEquals(ERROR_MESSAGE_NO_CELL, exception.getMessage());

    }

    private Atm fillAtm() {
        Atm atm = new Atm();
        service.addCurrency(atm, Currency.ONE_HUNDRED, 10);
        service.addCurrency(atm, Currency.FIVE_HUNDRED, 10);
        service.addCurrency(atm, Currency.ONE_THOUSAND, 10);
        service.addCurrency(atm, Currency.FIVE_THOUSAND, 10);
        return atm;
    }
}