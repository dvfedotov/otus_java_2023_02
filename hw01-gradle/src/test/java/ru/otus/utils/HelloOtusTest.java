package ru.otus.utils;

import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.utils.HelloOtus.countOccurrences;
import static ru.otus.utils.HelloOtus.countOccurrencesGuava;

class HelloOtusTest {

    public static final String REGEX = " ";
    public static final String INPUT_ARGUMENTS = "one two two";
    public static final String ONE = "one";
    public static final String TWO = "two";

    @Test
    void countOccurrences_success() {
        String[] args = INPUT_ARGUMENTS.split(REGEX);

        Map<String, Integer> actual = countOccurrences(args);

        assertAll(
                () -> assertEquals(1, actual.get(ONE)),
                () -> assertEquals(2, actual.get(TWO)));
    }

    @Test
    void countOccurrencesGuava_success() {
        String[] args = INPUT_ARGUMENTS.split(REGEX);

        Multiset<String> actual = countOccurrencesGuava(args);

        assertAll(
                () -> assertEquals(1, actual.count(ONE)),
                () -> assertEquals(2, actual.count(TWO)));
    }
}