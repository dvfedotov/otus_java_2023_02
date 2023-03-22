package ru.otus.homework;

import java.util.ArrayDeque;
import java.util.Deque;


public class CustomerReverseOrder {

    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Deque<Customer> queue = new ArrayDeque<>();

    public void add(Customer customer) {
        queue.add(customer);
    }

    public Customer take() {
        return queue.pollLast();
    }
}
