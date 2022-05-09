package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        count++;
        modCount++;
        if (Math.abs(capacity * LOAD_FACTOR - count) < 0.1) {
            expand();
        }
        int hk = hash(key.hashCode());
        int index = indexFor(hk);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
        }
        return rsl;
    }

    private int hash(int hashCode) {
        int h;
        return (h = hashCode) ^ h >>> 16;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        MapEntry[] old = Arrays.copyOf(table, capacity);
        capacity *= 1.5f;
        table = new MapEntry[capacity];
        for (MapEntry el: old) {
            if (el != null) {
                int hk = hash(el.key.hashCode());
                int index = indexFor(hk);
                table[index] = el;
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        if (key != null) {
            int index = indexFor(hash(key.hashCode()));
            if (table[index] != null) {
                rsl = (V) table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = get(key) != null;
        if (rsl) {
            int index = indexFor(hash(key.hashCode()));
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
