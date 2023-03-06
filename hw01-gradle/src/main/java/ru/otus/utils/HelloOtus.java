package ru.otus.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class HelloOtus {

    public static Map<String, Integer> countOccurrences(String[] args) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : args) {
            if (!countMap.containsKey(word)) {
                countMap.put(word, 0);
            }
            countMap.put(word, countMap.get(word) + 1);
        }
        return countMap;
    }

    public static Multiset<String> countOccurrencesGuava(String[] args) {
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(List.of(args));
        return wordsMultiset;
    }
}
