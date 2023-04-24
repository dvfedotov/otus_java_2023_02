package ru.otus.homework.model;

import org.junit.jupiter.api.Test;
import ru.otus.homework.exception.AtmException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmTest {

    public static final String ERROR_MESSAGE_NO_CASH = "Failed to receive the amount because there is no cash in the ATM";
    public static final String ERROR_MESSAGE_NOT_ENOUGH_CASH = "Failed to receive the amount because there is not enough cash in the ATM";
    public static final String ERROR_MESSAGE_NO_BANKNOTES = "Failed to receive the amount because there are no necessary banknotes in the ATM";

    @Test
    void getBalance_success() {
        Atm atm = fillAtm();

        int actual = atm.getBalance();

        assertEquals(66000, actual);
    }

    @Test
    void getCash_success() {
        Atm atm = fillAtm();

        Map<Currency, Integer> actual = atm.getCash(66000);

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

        AtmException exception = assertThrows(AtmException.class, () -> atm.getCash(66000));

        assertEquals(ERROR_MESSAGE_NO_CASH, exception.getMessage());
    }

    @Test
    void getCash_whenNotEnoughCash_throwException() {
        Atm atm = fillAtm();

        AtmException exception = assertThrows(AtmException.class, () -> atm.getCash(100000));

        assertEquals(ERROR_MESSAGE_NOT_ENOUGH_CASH, exception.getMessage());
    }

    @Test
    void getCash_whenNoBanknotes_throwException() {
        Atm atm = fillAtm();

        AtmException exception = assertThrows(AtmException.class, () -> atm.getCash(6782));

        assertEquals(ERROR_MESSAGE_NO_BANKNOTES, exception.getMessage());
    }

    private Atm fillAtm() {
        Atm atm = new Atm();
        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 10);
        atm.addCurrency(Currency.ONE_THOUSAND, 10);
        atm.addCurrency(Currency.FIVE_THOUSAND, 10);
        return atm;
    }

}