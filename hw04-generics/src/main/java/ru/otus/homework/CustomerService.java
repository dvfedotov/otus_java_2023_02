package ru.otus.homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> treeMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return Map.entry(new Customer(treeMap.firstKey()), treeMap.get(treeMap.firstKey()));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var optionalEntry = treeMap.entrySet().stream()
                .filter(m -> m.getKey().getScores() > customer.getScores())
//                .min(Map.Entry.comparingByKey(treeMap.comparator()));
                .findFirst();
        return optionalEntry.map(c -> Map.entry(new Customer(c.getKey()), c.getValue())).orElse(null);
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }
}
