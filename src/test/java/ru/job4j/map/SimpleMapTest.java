package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutOneEntry() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(1));
        assertEquals(map.get(1), "one");
    }

    @Test
    public void whenPutTwoEntriesWithDifferentKeys() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertTrue(map.put(42, "two"));
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(42));
    }

    @Test
    public void whenPutTwoEntriesWithSameKeys() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertFalse(map.put(1, "two"));
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(1));
        assertThat(map.get(1), Is.is("one"));
    }

    @Test
    public void whenExceedCapacity() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertTrue(map.put(2, "two"));
        assertTrue(map.put(3, "three"));
        assertTrue(map.put(4, "four"));
        assertTrue(map.put(5, "five"));
        assertTrue(map.put(6, "six"));
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), Is.is(1));
        assertThat(iterator.next(), Is.is(2));
        assertThat(iterator.next(), Is.is(3));
        assertThat(iterator.next(), Is.is(4));
        assertThat(iterator.next(), Is.is(5));
        assertThat(iterator.next(), Is.is(6));
    }

    @Test
    public void whenRemoveExistingEntry() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertEquals(map.get(1), "one");
        assertTrue(map.remove(1));
        Iterator<Integer> iterator = map.iterator();
        assertFalse(iterator.hasNext());
        assertNull(map.get(1));
    }

    @Test
    public void whenRemoveNonExistentEntry() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertFalse(map.remove(2));
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(1));
    }

}