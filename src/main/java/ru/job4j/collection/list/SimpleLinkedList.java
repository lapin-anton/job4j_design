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
        Node<E> current = first;
        for (int i = 1; i <= index; i++) {
            current = current.getNext();
        }
        return current.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null
                        && (currentNode.equals(first)
                        || currentNode.getNext() != null
                        || currentNode.equals(last));
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = currentNode.getItem();
                currentNode = currentNode.getNext();
                return value;
            }
        };
    }
}
