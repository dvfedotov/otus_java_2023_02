package ru.otus.processor.homework;

import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangerProcessorTest {

    @Test
    void process() {
        var changerProcessor = new ChangerProcessor();

        var id = 100L;

        var message = new Message.Builder(id)
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .build();

        var actual = changerProcessor.process(message);

        assertEquals("field12", actual.getField11());
        assertEquals("field11", actual.getField12());
    }
}