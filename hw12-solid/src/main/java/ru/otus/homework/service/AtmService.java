package ru.otus.homework.service;

import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Currency;

import java.util.Map;

public interface AtmService {

    void addCurrency(Atm atm, Currency currency, int count);

    Map<Currency, Integer> getCash(Atm atm, int sum);

}
