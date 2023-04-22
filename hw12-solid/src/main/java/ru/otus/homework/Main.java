package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Currency;

import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) {

        Atm atm = new Atm();
        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 1);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        Map<Currency, Integer> cashMap1 = atm.getMoney(1000);
        log.info("cashMap [{}] ", cashMap1);
        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 10);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        Map<Currency, Integer> cashMap2 = atm.getMoney(1300);
        log.info("cashMap [{}] ", cashMap2);
        atm.addCurrency(Currency.ONE_THOUSAND, 100);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        Map<Currency, Integer> cashMap3 = atm.getMoney(13200);
        log.info("cashMap [{}] ", cashMap3);
        atm.addCurrency(Currency.ONE_THOUSAND, 50);
        atm.addCurrency(Currency.FIVE_THOUSAND, 10);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        Map<Currency, Integer> cashMap4 = atm.getMoney(121600);
        log.info("cashMap [{}] ", cashMap4);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
    }
}