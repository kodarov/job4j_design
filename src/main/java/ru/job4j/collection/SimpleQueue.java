package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int sizeInput;
    private int sizeOutput;

    public T poll() {
        if (sizeInput == 0 && sizeOutput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeInput > 0 && sizeOutput == 0) {
            while (sizeInput > 0) {
                output.push(input.pop());
                sizeInput--;
                sizeOutput++;
            }
        }
        T item = output.pop();
        sizeOutput--;
        return item;
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}