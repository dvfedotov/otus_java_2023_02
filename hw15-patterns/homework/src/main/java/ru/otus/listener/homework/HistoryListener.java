package ru.otus.listener.homework;

import lombok.extern.slf4j.Slf4j;
import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> historyMessageMap = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        try {
            historyMessageMap.put(msg.getId(), (Message) msg.clone());
        } catch (CloneNotSupportedException e) {
            log.error("Error when clone message [{}]", msg, e);
        }
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(historyMessageMap.get(id));
    }
}
