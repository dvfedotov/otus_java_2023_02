package ru.otus.homework.service;

import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Currency;

public interface AtmService {

    void addCurrency(Atm atm, Currency currency, int count);

}
