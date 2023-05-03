package ru.otus.homework.service;

import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Currency;

import java.util.Map;

public interface AtmCashService {

    Map<Currency, Integer> getCash(Atm atm, int sum);

}
