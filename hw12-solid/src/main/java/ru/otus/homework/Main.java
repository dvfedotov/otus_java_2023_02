package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmCashService;
import ru.otus.homework.service.impl.AtmCashServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class Main {

    public static final String ATM_1_MESSAGE = "ATM_1 dispensed [{}] balance [{}]";
    public static final String ATM_2_MESSAGE = "ATM_2 dispensed [{}] balance [{}]";

    public static void main(String[] args) {
        AtmCashService cashService = new AtmCashServiceImpl();

        Atm atm = fillAtm1();
        cashService.addCash(atm, Currency.ONE_HUNDRED, 10);
        cashService.addCash(atm, Currency.FIVE_HUNDRED, 1);
        Map<Currency, Integer> cashMap1 = cashService.getCash(atm, 1000);
        log.info(ATM_1_MESSAGE, cashMap1, atm.getBalance());

        Atm atm2 = fillAtm2();
        cashService.addCash(atm2, Currency.ONE_HUNDRED, 10);
        cashService.addCash(atm2, Currency.FIVE_HUNDRED, 10);
        Map<Currency, Integer> cashMap5 = cashService.getCash(atm2, 2200);
        log.info(ATM_2_MESSAGE, cashMap5, atm2.getBalance());

        cashService.addCash(atm, Currency.ONE_HUNDRED, 10);
        cashService.addCash(atm, Currency.FIVE_HUNDRED, 10);
        Map<Currency, Integer> cashMap2 = cashService.getCash(atm, 1300);
        log.info(ATM_1_MESSAGE, cashMap2, atm.getBalance());

        cashService.addCash(atm, Currency.ONE_THOUSAND, 100);
        Map<Currency, Integer> cashMap3 = cashService.getCash(atm, 13200);
        log.info(ATM_1_MESSAGE, cashMap3, atm.getBalance());

        cashService.addCash(atm, Currency.ONE_THOUSAND, 50);
        cashService.addCash(atm, Currency.FIVE_THOUSAND, 10);
        Map<Currency, Integer> cashMap4 = cashService.getCash(atm, 121600);
        log.info(ATM_1_MESSAGE, cashMap4, atm.getBalance());


    }

    private static Atm fillAtm1() {
        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(Currency.ONE_HUNDRED));
        cellList.add(new Cell(Currency.FIVE_HUNDRED));
        cellList.add(new Cell(Currency.ONE_THOUSAND));
        cellList.add(new Cell(Currency.FIVE_THOUSAND));
        return new Atm(cellList);
    }

    private static Atm fillAtm2() {
        List<Cell> cellList2 = new ArrayList<>();
        cellList2.add(new Cell(Currency.ONE_HUNDRED));
        cellList2.add(new Cell(Currency.FIVE_HUNDRED));
        cellList2.add(new Cell(Currency.ONE_THOUSAND));
        return new Atm(cellList2);
    }
}