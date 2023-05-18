package ru.otus.processor.homework;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionProcessorTest {

    public static final long ID = 100L;

    @Test
    void process_whenOddSecond_success() {
        var exceptionProcessor = new ExceptionProcessor(() -> LocalDateTime.of(2023, 5, 18, 10, 10, 11));

        assertDoesNotThrow(() -> exceptionProcessor.process(getMessage()));
    }

    @Test
    @SneakyThrows
    void process_whenEvenSecond_throwException() {
        var exceptionProcessor = new ExceptionProcessor(() -> LocalDateTime.of(2023, 5, 18, 10, 10, 22));

        assertThrows(EvenSecondException.class, () -> exceptionProcessor.process(getMessage()));
    }

    private Message getMessage() {
        return new Message.Builder(ID).build();
    }
}