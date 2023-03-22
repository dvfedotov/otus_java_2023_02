package ru.otus.homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> treeMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Customer smallestCustomer = treeMap.firstKey();
        if (smallestCustomer == null) {
            return null;
        }
        return Map.entry(new Customer(smallestCustomer), treeMap.get(smallestCustomer));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer nextCustomer = treeMap.higherKey(customer);
        if (nextCustomer == null) {
            return null;
        }
        return Map.entry(new Customer(nextCustomer), treeMap.get(nextCustomer));
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }
}
