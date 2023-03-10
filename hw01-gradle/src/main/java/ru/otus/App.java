package ru.otus;

import com.google.common.collect.Multiset;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static ru.otus.utils.HelloOtus.countOccurrences;
import static ru.otus.utils.HelloOtus.countOccurrencesGuava;

@Slf4j
public class App {
    public static void main(String... args) {
        Map<String, Integer> countMap = countOccurrences(args);
        log.info("Word occurrence statistics [{}]", countMap);

        Multiset<String> countMapGuava = countOccurrencesGuava(args);
        log.info("Word occurrence statistics with guava Multiset [{}]", countMapGuava);
    }
}
