package ru.otus.processor.homework;

import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorEvenSecond implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getTime().getSecond() % 2 == 0) {
            throw new EvenSecondException();
        }
        return message;
    }
}
