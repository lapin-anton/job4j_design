package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return out.pop();
    }

    public void push(T value) {
        if (size > 0) {
            copyElements(out, in);
        }
        in.push(value);
        size++;
        copyElements(in, out);
    }

    private void copyElements(SimpleStack<T> src, SimpleStack<T> target) {
        for (int i = 0; i < size; i++) {
            target.push(src.pop());
        }
    }
}
