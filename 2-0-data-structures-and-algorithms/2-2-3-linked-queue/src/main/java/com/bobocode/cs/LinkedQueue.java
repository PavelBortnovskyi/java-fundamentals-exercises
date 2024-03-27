package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {
    private static class Node<T> {
        T element;
        Node<T> nextNode;

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        private Node(T element) {
            this.element = element;
        }
    }

    int size = 0;
    Node<T> head;
    Node<T> tail;

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        // todo: implement this method
        Node<T> node = Node.valueOf(element);

        if (this.isEmpty()) {
            head = node;
            tail = head;
        } else {
           tail.nextNode = node;
           tail = node;
        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        // todo: implement this method
        if (this.isEmpty()) return null;
        T element = head.element;
        head = head.nextNode;
        if (head == null) tail = null;
        size--;
        return element;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
       // todo: implement this method
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        // todo: implement this method
        return size == 0;
    }
}
