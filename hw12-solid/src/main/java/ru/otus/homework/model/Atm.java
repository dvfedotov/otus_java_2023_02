package ru.otus.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@ToString
public class Atm {

    private List<Cell> cellList;

    public void addCurrency(Currency currency, int count) {
        if (CollectionUtils.isEmpty(cellList)) {
            cellList = new ArrayList<>();
        }
        Optional<Cell> optionalCell = cellList.stream().filter(c -> c.getCurrency().equals(currency)).findFirst();
        if (optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            cell.setCount(cell.getCount() + count);
        } else {
            cellList.add(new Cell(currency, count));
        }
    }


    public int getBalance() {
        if (CollectionUtils.isEmpty(cellList)) {
            return 0;
        }
        return cellList.stream()
                .mapToInt(c -> c.getCurrency().getValue() * c.getCount())
                .reduce(0, Integer::sum);
    }

    public void getMoney(int sum) {
        if (sum > getBalance()) {
            log.info("Could not get sum [{}] balance [{}]", sum, getBalance());
            return;
        }
        if (CollectionUtils.isEmpty(cellList)) {
            log.info("Could not get sum [{}] because no money", sum);
            return;
        }
        cellList.sort(Comparator.comparingInt(c -> c.getCurrency().getValue()));
        Collections.reverse(cellList);
        Map<Currency, Integer> cashMap = new EnumMap<>(Currency.class);
        final int[] tempSum = {sum};
        for (Cell cell : cellList) {
            int currency = cell.getCurrency().getValue();
            int x = tempSum[0] / currency;
            if (x > 0) {
                if (cell.getCount() >= x) {
                    cashMap.put(cell.getCurrency(), x);
                    tempSum[0] = tempSum[0] - currency * x;
                } else {
                    cashMap.put(cell.getCurrency(), cell.getCount());
                    tempSum[0] = tempSum[0] - cell.getCount() * currency;
                }
            }
        }

        if (tempSum[0] > 0) {
            log.info("Could not get sum [{}] choose another sum", sum);
            return;
        }
        for (Map.Entry<Currency, Integer> entry : cashMap.entrySet()) {
            Optional<Cell> optionalCell = cellList.stream().filter(c -> c.getCurrency().equals(entry.getKey())).findFirst();
            optionalCell.ifPresent(cell -> {
                int count = cell.getCount() - entry.getValue();
                if (count <= 0) {
                    cellList.remove(cell);
                } else {
                    cell.setCount(count);
                }
            });
        }


        log.info("cashMap [{}], list[{}] ", cashMap, cellList);
    }
}
