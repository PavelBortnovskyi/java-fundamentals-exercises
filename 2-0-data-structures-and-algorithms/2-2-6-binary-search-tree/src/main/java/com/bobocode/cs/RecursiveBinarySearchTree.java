package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private static class Node<T> {
        T value;
        Node<T> rightNode;
        Node<T> leftNode;

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }

        private Node(T element) {
            this.value = element;
        }
    }

    Node<T> root;
    int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (contains(element)) return false;
        else {
            if (root == null) root = Node.valueOf(element);
            else recursiveInsert(element, root);
            size++;
            return true;
        }
    }


    private boolean recursiveInsert(T element, Node<T> node) {
        if (element.compareTo(node.value) > 0 && node.rightNode == null) {
            node.rightNode = Node.valueOf(element);
            return true;
        } else if (element.compareTo(node.value) < 0 && node.leftNode == null) {
            node.leftNode = Node.valueOf(element);
            return true;
        } else return recursiveInsert(element, element.compareTo(node.value) > 0 ? node.rightNode : node.leftNode);
    }

    @Override
    public boolean contains(T element) {
        if (element == null) throw new NullPointerException();
        if (size == 0) return false;
        if (size == 1) return element.compareTo(root.value) == 0;
        return recursiveContains(element, root);
    }

    private boolean recursiveContains(T element, Node<T> node) {
        if (node == null) return false;
        if (node.value.compareTo(element) == 0) return true;
        if (node.leftNode == null && node.rightNode == null) return false;
        else if (element.compareTo(node.value) > 0) return recursiveContains(element, node.rightNode);
        else if (element.compareTo(node.value) < 0) return recursiveContains(element, node.leftNode);
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (size <= 1) return 0;
        return diveCount(0, root);
    }

    private int diveCount(int depthCounter, Node<T> node) {
        if (node == null || (node.rightNode == null && node.leftNode == null)) return depthCounter;
        return Math.max(diveCount(depthCounter + 1, node.leftNode), diveCount(depthCounter + 1, node.rightNode));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.leftNode, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.rightNode, consumer);
        }
    }
}
