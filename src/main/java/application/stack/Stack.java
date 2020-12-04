package application.stack;


import java.util.NoSuchElementException;

public class Stack<T> implements StackInterface<T> {

    private Node<T> top = null;

    /**
     * Helper Class for GenericLinkedStack.
     */
    private static class Node<T> {
        private T data;
        private Node<T> next = null;

        Node(T element) {
            data = element;
        }
    }

    @Override
    public StackInterface<T> push(T element) {
        Node<T> newItem = new Node<T>(element);

        if (top == null) {
            top = newItem;
        } else {
            // New Top
            newItem.next = top;
            top = newItem;
        }

        return this;
    }

    @Override
    public T pop() {
        if (top == null) {
            throw new NoSuchElementException("The stack is empty.");
        }

        T output = top.data;
        top = top.next;

        return output;
    }

    @Override
    public T peek() {
        if (top == null) {
            throw new NoSuchElementException("The stack is empty.");
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}