package ru.otus.homework;


import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Map<Customer, String> map = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        log.info("getSmallest");
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        var optionalEntry = map.entrySet().stream()
                .min(Map.Entry.comparingByKey(Comparator.comparing(Customer::getScores)));
        return optionalEntry.map(c -> Map.entry(new Customer(c.getKey()), c.getValue())).orElse(null);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        log.info("getNext Customer [{}]", customer);
        var optionalEntry = map.entrySet().stream()
                .filter(m -> m.getKey().getScores() > customer.getScores())
                .min(Map.Entry.comparingByKey(Comparator.comparing(Customer::getScores)));
        return optionalEntry.map(c -> Map.entry(new Customer(c.getKey()), c.getValue())).orElse(null);
    }

    public void add(Customer customer, String data) {
        log.info("add Customer [{}], data [{}]", customer, data);
        map.put(customer, data);
    }
}
