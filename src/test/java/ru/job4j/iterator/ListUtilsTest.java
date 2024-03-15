package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(4).containsSequence(0, 1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(list, e -> e > 2);
        assertThat(list).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenReplaceIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(list, e -> e > 2, 1);
        assertThat(list).hasSize(4).containsSequence(1, 2, 1, 1);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> elements = List.of(1, 4, 6);
        ListUtils.removeAll(list, elements);
        assertThat(list).hasSize(3).containsSequence(2, 3, 5);
    }
}