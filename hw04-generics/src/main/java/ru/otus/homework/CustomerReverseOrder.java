package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;

@Slf4j
public class CustomerReverseOrder {

    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final ArrayDeque<Customer> queue = new ArrayDeque<>();

    public void add(Customer customer) {
        queue.add(customer);

    }

    public Customer take() {
        return queue.pollLast();
    }
}
