package ru.job4j.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        var parentNode = findBy(parent);
        if (!parent.equals(child)
                && parentNode.isPresent()
                && findBy(child).isEmpty()) {
            List<Node<E>> listNodes = parentNode.get().children;
            result = listNodes.add(new Node<>(child));
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> condition = el -> el.value.equals(value);
        return findByPredicate(condition);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> condition = el -> el.children.size() > 2;
        return findByPredicate(condition).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}