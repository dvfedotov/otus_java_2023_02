package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Currency;

import java.util.Map;

@Slf4j
public class Main {

    public static final String ATM_MESSAGE = "ATM dispensed [{}] balance [{}]";

    public static void main(String[] args) {

        Atm atm = new Atm();
        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 1);
        Map<Currency, Integer> cashMap1 = atm.getCash(1000);
        log.info(ATM_MESSAGE, cashMap1, atm.getBalance());

        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 10);
        Map<Currency, Integer> cashMap2 = atm.getCash(1300);
        log.info(ATM_MESSAGE, cashMap2, atm.getBalance());

        atm.addCurrency(Currency.ONE_THOUSAND, 100);
        Map<Currency, Integer> cashMap3 = atm.getCash(13200);
        log.info(ATM_MESSAGE, cashMap3, atm.getBalance());

        atm.addCurrency(Currency.ONE_THOUSAND, 50);
        atm.addCurrency(Currency.FIVE_THOUSAND, 10);
        Map<Currency, Integer> cashMap4 = atm.getCash(121600);
        log.info(ATM_MESSAGE, cashMap4, atm.getBalance());
    }
}