package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Cell {

    private Currency currency;
    private int count;

    public Cell(Currency currency) {
        this.currency = currency;
    }

    public void addCash(int count) {
        if (count > 0) {
            this.count += count;
        }
    }

    public void getCash(int count){
        if(count >= this.count){
            this.count-=count;
        }
    }

}
