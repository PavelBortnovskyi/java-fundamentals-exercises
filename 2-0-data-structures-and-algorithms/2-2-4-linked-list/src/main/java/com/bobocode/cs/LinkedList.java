package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;
import net.bytebuddy.jar.asm.ClassTooLargeException;

import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

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
    Node<T> first;
    Node<T> last;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        // todo: implement this method
        Node<T> node = Node.valueOf(element);
        if (size == 0) {
            addAsFirst(element);
        } else {
            addAsLast(element);
        }
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        // todo: implement this method
        if (index == 0) {
            addAsFirst(element);
            return;
        }

        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();

        Node<T> node = Node.valueOf(element);
        Node<T> currNode = first;
            for (int i = 1; i < index; i++) {
                currNode = currNode.nextNode;
            }
            node.nextNode = currNode.nextNode;
            currNode.nextNode = node;
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        // todo: implement this method
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
        Node<T> currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.nextNode;
        }
        currNode.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        // todo: implement this method
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
        Node<T> currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.nextNode;
        }
        return currNode.element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        // todo: implement this method
        if (isEmpty()) throw new NoSuchElementException();
        return first.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        // todo: implement this method
        if (isEmpty()) throw new NoSuchElementException();
        return last.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        // todo: implement this method
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        // todo: implement this method
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    private void addAsFirst(T element) {
        Node<T> node = Node.valueOf(element);
        node.nextNode = first;
        first = node;
        if (first.nextNode == null) {
            last = first;
        }
        size++;
    }

    private void addAsLast(T element) {
        Node<T> node = Node.valueOf(element);
        last.nextNode = node;
        last = node;
        size++;
    }
}
