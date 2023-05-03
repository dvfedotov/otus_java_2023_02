package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmService;
import ru.otus.homework.service.impl.AtmServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class Main {

    public static final String ATM_MESSAGE = "ATM dispensed [{}] balance [{}]";

    public static void main(String[] args) {

        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(Currency.ONE_HUNDRED));
        cellList.add(new Cell(Currency.FIVE_HUNDRED));
        cellList.add(new Cell(Currency.ONE_THOUSAND));
        cellList.add(new Cell(Currency.FIVE_THOUSAND));
        Atm atm = new Atm(cellList);
        AtmService atmService = new AtmServiceImpl();
        atmService.addCurrency(atm, Currency.ONE_HUNDRED, 10);
        atmService.addCurrency(atm, Currency.FIVE_HUNDRED, 1);
        Map<Currency, Integer> cashMap1 = atmService.getCash(atm,1000);
        log.info(ATM_MESSAGE, cashMap1, atmService.getBalance(atm));

        atmService.addCurrency(atm,Currency.ONE_HUNDRED, 10);
        atmService.addCurrency(atm,Currency.FIVE_HUNDRED, 10);
        Map<Currency, Integer> cashMap2 = atmService.getCash(atm,1300);
        log.info(ATM_MESSAGE, cashMap2, atmService.getBalance(atm));

        atmService.addCurrency(atm,Currency.ONE_THOUSAND, 100);
        Map<Currency, Integer> cashMap3 = atmService.getCash(atm,13200);
        log.info(ATM_MESSAGE, cashMap3, atmService.getBalance(atm));

        atmService.addCurrency(atm,Currency.ONE_THOUSAND, 50);
        atmService.addCurrency(atm,Currency.FIVE_THOUSAND, 10);
        Map<Currency, Integer> cashMap4 = atmService.getCash(atm,121600);
        log.info(ATM_MESSAGE, cashMap4, atmService.getBalance(atm));
    }
}