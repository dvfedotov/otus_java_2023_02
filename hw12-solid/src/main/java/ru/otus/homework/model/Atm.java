package ru.otus.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
        log.info("List [{}] ", cellList);
        Collections.reverse(cellList);
        log.info("List [{}] ", cellList);
        Map<Cell, Integer> map = new HashMap<>();
        final int[] tempSum = {sum};
        cellList.forEach(c -> {
                    int currency = c.getCurrency().getValue();
                    int x = tempSum[0] / currency;
                    if (x > 0) {
                        if (c.getCount() >= x) {
                            map.put(c, x);
                            tempSum[0] -= currency * x;
                        } else {
                            map.put(c, c.getCount());
                            tempSum[0] -= c.getCount() * currency;
                        }
                    }
                }
        );
        log.info("Map [{}] ", map);
    }
}
