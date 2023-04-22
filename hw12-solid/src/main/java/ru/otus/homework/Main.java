package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        Atm atm = new Atm();
        Cell cellOne = new Cell(Currency.ONE_HUNDRED,10);
        Cell cellTwo = new Cell(Currency.FIVE_HUNDRED, 1);
        List<Cell> cellList = new ArrayList<>();
        cellList.add(cellOne);
        cellList.add(cellTwo);
        atm.setCellList(cellList);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        atm.getMoney(1000);
        atm.addCurrency(Currency.ONE_HUNDRED, 10);
        atm.addCurrency(Currency.FIVE_HUNDRED, 10);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        atm.getMoney(1310);
        atm.addCurrency(Currency.ONE_THOUSAND, 100);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        atm.getMoney(13200);
        atm.addCurrency(Currency.ONE_THOUSAND, 50);
        atm.addCurrency(Currency.FIVE_THOUSAND, 10);
        log.info("log {}", atm);
        log.info("Balance [{}]", atm.getBalance());
        atm.getMoney(121600);
    }
}