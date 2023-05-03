package ru.otus.homework.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.exception.AtmException;
import ru.otus.homework.model.Atm;
import ru.otus.homework.model.Cell;
import ru.otus.homework.model.Currency;
import ru.otus.homework.service.AtmService;

import java.util.Optional;

@Slf4j
public class AtmServiceImpl implements AtmService {

    public static final String ERROR_MESSAGE_NO_CELL = "Unable to add currency because required cell is missing";


    @Override
    public void addCurrency(@NonNull Atm atm, Currency currency, int count) {
        Optional<Cell> optionalCell = atm.findCell(currency);
        optionalCell.ifPresentOrElse(c -> c.addCash(count),
                () -> {
                    throw new AtmException(ERROR_MESSAGE_NO_CELL);
                });
    }
}
