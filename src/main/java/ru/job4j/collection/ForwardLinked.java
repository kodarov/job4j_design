package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> l = head;
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        int iter = 0;
        T item = null;
        Objects.checkIndex(index, size);
        Iterator<T> iterator = iterator();
        while (iter <= index) {
            if (iterator().hasNext()) {
                item = iterator.next();
            }
            iter++;
        }
        return item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T item = head.item;
        Node<T> h = head;
        head = head.next;
        h.item = null;
        h.next = null;
        h = null;
        size--;
        modCount++;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            Node<T> cursor = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = cursor.item;
                cursor = cursor.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}