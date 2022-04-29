package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Predicate<Integer> filter = num -> num % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, Is.is(Arrays.asList(1, 3)));
    }

    @Test
    public void whenReplaceMinusOneIfOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Predicate<Integer> filter = num -> num % 2 != 0;
        ListUtils.replaceIf(input, filter, -1);
        assertThat(input, Is.is(Arrays.asList(0, -1, 2, -1)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> removed = new ArrayList<>(Arrays.asList(0, 2));
        ListUtils.removeAll(input, removed);
        assertThat(input, Is.is(Arrays.asList(1, 3)));
    }

}