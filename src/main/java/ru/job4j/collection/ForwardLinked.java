package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        Node<T> last = tail;
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
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
        T item;
        if (head != null) {
            Node<T> tmp = head;
            item = tmp.item;
            head = head.next;
            tmp.item = null;
            tmp = null;
            size--;
            modCount++;
        } else {
            throw new NoSuchElementException();
        }
        return item;
    }

    public void addFirst(T value) {
        Node<T> first = head;
        head = new Node<>(value, first);
        modCount++;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            int index;
            Node<T> cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (cursor == null) {
                    cursor = head;
                } else {
                    cursor = cursor.next;
                }
                index++;
                return cursor.item;
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