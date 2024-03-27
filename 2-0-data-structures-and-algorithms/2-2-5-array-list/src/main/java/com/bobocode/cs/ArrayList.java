package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {
    static final int defaultCapacity = 5;
    private Object[] data;
    private int size = 0;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) throw new IllegalArgumentException();
        data = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this(defaultCapacity);
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        list.data = Arrays.copyOf(elements, elements.length);
        list.size = elements.length;
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        resizeDataIfFull();
        data[size] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        // todo: implement this method
        if (index < 0) throw new IndexOutOfBoundsException();
        resizeDataIfFull();
        //System.arraycopy(data, index, data, index + 1, size - index);

        Object[] frontPart = Arrays.copyOfRange(data, 0, index);
        Object[] backPart = Arrays.copyOfRange(data, index, data.length);
        data = new Object[data.length];

        data = insertToArray(data, frontPart,0);
        data[index] = element;
        data = insertToArray(data, backPart, index + 1);
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) data[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) data[data.length - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        // todo: implement this method
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        data[index] = element;
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
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        T deletedElement = (T) data[index];
        Object[] frontPart = Arrays.copyOfRange(data, 0, index);
        Object[] backPart = Arrays.copyOfRange(data, index + 1, data.length);
        insertToArray(data, frontPart, 0);
        insertToArray(data, backPart, index);
        size--;
        return deletedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        // todo: implement this method
        if (isEmpty()) return false;
        return Arrays.stream(data).anyMatch(dataElement -> dataElement.equals(element));
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;// todo: implement this method
    }

    /**
     * @return amount of saved elements
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
        // todo: implement this method
        data = new Object[defaultCapacity];
        size = 0;
    }

    private void resizeDataIfFull() {
        if (data.length == size) {
            data = Arrays.copyOf(data, (int) Math.round(size * 2));
        }
    }

    private Object[] insertToArray(Object[] targetArr, Object[] sourceArr, int prefix) {
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] != null) targetArr[prefix + i] = sourceArr[i];
        }
        return targetArr;
    }
}
