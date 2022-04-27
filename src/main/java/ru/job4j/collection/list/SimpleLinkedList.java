package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        modCount++;
        size++;
        Node<E> prevLast = last;
        Node<E> newNode = new Node<>(value, last, null);
        last = newNode;
        if (prevLast == null) {
            first = newNode;
        } else {
            prevLast.setNext(newNode);
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Iterator<E> it = iterator();
        E value = null;
        if (index == 0) {
            value = first.getItem();
        } else {
            for (int i = 0; i <= index; i++) {
                value = it.next();
            }
        }
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> currentNode;
            private int iteration;

            @Override
            public boolean hasNext() {
                boolean rsl;
                if (currentNode == null && iteration == 0) {
                    rsl = first != null;
                } else {
                    rsl = currentNode.getNext() != null;
                }
                return rsl;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (iteration == 0) {
                    currentNode = first;
                } else {
                    currentNode = currentNode.getNext();
                }
                iteration++;
                return currentNode.getItem();
            }
        };
    }
}
