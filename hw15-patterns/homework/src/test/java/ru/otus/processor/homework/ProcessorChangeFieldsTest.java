package ru.otus.processor.homework;

import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessorChangeFieldsTest {

    @Test
    void process() {
        var changerProcessor = new ProcessorChangeFields();

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