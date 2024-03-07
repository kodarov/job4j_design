package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        Node<E> last = tail;
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        int iter = 0;
        E item = null;
        Objects.checkIndex(index, size);
        Iterator<E> iterator = iterator();
        while (iter <= index) {
            if (iterator().hasNext()) {
                item = iterator.next();
            }
            iter++;
        }
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            int index;
            Node<E> cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
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

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}