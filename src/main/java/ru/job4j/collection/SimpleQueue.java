package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        copyElements(in, out);
        return out.pop();
    }

    public void push(T value) {
        copyElements(out, in);
        in.push(value);
    }

    private void copyElements(SimpleStack<T> src, SimpleStack<T> target) {
        while (true) {
            try {
                T el = src.pop();
                target.push(el);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
