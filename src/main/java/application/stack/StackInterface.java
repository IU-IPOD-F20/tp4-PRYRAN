package application.stack;

import java.util.NoSuchElementException;

public interface StackInterface<T> {
    /**
     * Pushes an element onto the stack and returns this class to allow method chaining.
     *
     * @param element
     *            - A generic element to push onto the stack
     */
    StackInterface<T> push(T element);

    /**
     * Removes and returns the last element that was added to the stack.
     *
     * @return The last element of the stack.
     * @throws NoSuchElementException
     *             is thrown when there are no elements to pop off the stack
     */
    T pop() throws NoSuchElementException;

    /**
     * Returns the last element that was added to the stack.
     *
     * @return The last element of the stack.
     * @throws NoSuchElementException
     *             is thrown when there are no elements to peek for the stack
     */
    T peek() throws NoSuchElementException;

    /**
     * Returns a boolean whether the stack is empty.
     *
     * @return True if the stack is empty.
     */
    boolean isEmpty();
}