package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.LoggerProcessor;
import ru.otus.processor.ProcessorConcatFields;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.processor.homework.ProcessorChangeFields;
import ru.otus.processor.homework.ProcessorEvenSecond;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomeWork {
    public static final long ID = 1L;

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {

        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()),
                new LoggerProcessor(new ProcessorChangeFields()),
                new ProcessorEvenSecond(LocalDateTime::now));

        var complexProcessor = new ComplexProcessor(processors, ex -> {
        });
        var listenerPrinter = new ListenerPrinterConsole();
        complexProcessor.addListener(listenerPrinter);

        var message = new Message.Builder(ID)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(fillFiled13())
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        var historyListener = new HistoryListener();
        complexProcessor.addListener(historyListener);
        historyListener.onUpdated(message);
        message.getField13().setData(new ArrayList<>());
        var messageFromHistory = historyListener.findMessageById(ID);
        System.out.println("messageFromHistory:" + messageFromHistory);
        System.out.println("result:" + result);
        complexProcessor.removeListener(listenerPrinter);

    }

    private static ObjectForMessage fillFiled13() {
        var data = "33";
        var field13 = new ObjectForMessage();
        var field13Data = new ArrayList<String>();
        field13Data.add(data);
        field13.setData(field13Data);
        return field13;
    }
}
