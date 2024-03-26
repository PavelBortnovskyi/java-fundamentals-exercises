package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;
import org.apache.commons.math3.exception.NullArgumentException;

import java.util.Arrays;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {
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

    Node<T> head;
    int size = 0;

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        // todo: implement this method
        LinkedStack<T> stack = new LinkedStack<>();
        Arrays.stream(elements).forEach(stack::push);
        return stack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        // todo: implement this method
        if (element == null) throw new NullPointerException();

        Node<T> newNode = Node.valueOf(element);

        if (head != null) {
            newNode.nextNode = head;
        }
        head = newNode;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        // todo: implement this method
        if (size == 0) throw new EmptyStackException();
        size--;
        T headElement = head.element;
        head = head.nextNode;
        return headElement;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        // todo: implement this method
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        // todo: implement this method;
        return size == 0;
    }
}
