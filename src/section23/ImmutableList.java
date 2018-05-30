package section23;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

/**
 * This class implements a generic immutable Linked List that demonstrates the advantages of immutable data structures.
 *
 * Despite that functional programming favors recursion, all recursions in this example are unwrapped to optimize perf.
 */
public class ImmutableList<T> {

    /**
     * Static nested class that represents an immutable node that holds element type U.
     */
    private static class Node<U> {
        // Note that both data and next fields are `final`, i.e. immutable after initialization
        public final U data;
        public final Node<U> next;

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }
    }

    /* Private, immutable field that holds the head of the immutable Linked List. */
    private final Node<T> head;

    /* Constructor of empty/singleton linked list */
    public ImmutableList() {
        this(null);     // invoke another constructor
    }

    public ImmutableList(Node<T> head) {
        this.head = head;
    }

    /* Prepends a value to the linked list, and returns a new immutable linked list - O(1) */
    public ImmutableList<T> cons(T val) {
        Node<T> newHead = new Node<T>(val, head);
        ImmutableList<T> newList = new ImmutableList<>(newHead);
        return newList;
    }

    /* Utility method that returns the length of linked list - O(n) */
    public int length() {
        int result = 0;
        Node<T> iter = head;

        while (iter != null) {
            result++;
            iter = iter.next;
        }
        return result;
    }

    /* Returns an element of the linked list by index - O(n) */
    public T get(int idx) {
        if (idx < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        int steps = idx;
        Node<T> iter = head;
        while (steps > 0) {
            if (iter == null) {
                throw new IndexOutOfBoundsException("idx = " + idx);
            }
            iter = iter.next;
            steps--;
        }

        if (iter == null) {
            throw new IndexOutOfBoundsException("idx = " + idx);
        }
        return iter.data;
    }

    /* Applies a transformation function to all elements of the linked list and return a new immutable list */
    public <W> ImmutableList<W> map(Function<T, W> fn) {
        Stack<W> mappedValues = new Stack<>();
        Node<T> iter = head;

        while (iter != null) {
            mappedValues.push(fn.apply(iter.data));
        }

        ImmutableList<W> result = new ImmutableList<>();
        while (!mappedValues.isEmpty()) {
            result = result.cons(mappedValues.pop());
        }
        return result;
    }

    /* Inserts an element to selected index and returns a new immutable list */
    public ImmutableList<T> insert(T elem, int idx) {
        if (idx < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        int steps = idx;
        Node<T> iter = head;
        Stack<T> prefix = new Stack<>();

        while (steps > 0) {
            if (iter == null) {
                throw new IndexOutOfBoundsException("idx = " + idx);
            }
            prefix.push(iter.data);
            iter = iter.next;
            steps--;
        }

        // insert the element
        ImmutableList<T> result = new ImmutableList<>(new Node<>(elem, iter));

        // add in original prefix
        while (!prefix.isEmpty()) {
            result = result.cons(prefix.pop());
        }

        return result;
    }

    /* Return a new immutable list that is the reverse of current list */
    public ImmutableList<T> revert() {
        ImmutableList<T> result = new ImmutableList<>();

        Node<T> iter = head;
        while (iter != null) {
            result = result.cons(iter.data);
            iter = iter.next;
        }

        return result;
    }

    /* Returns a String representation of the linked list */
    public String toString() {
        StringBuilder resultBuffer = new StringBuilder("ImmutableList(");

        Node<T> node = head;
        while (node != null) {
            resultBuffer.append(node.data);
            resultBuffer.append(" - ");
        }

        resultBuffer.delete(resultBuffer.length() - 3, resultBuffer.length()).append(")");
        return resultBuffer.toString();
    }
}