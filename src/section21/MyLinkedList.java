package section21;

import java.util.Iterator;

/**
 * This class implements a generic doubly-linked list with common operations.
 */
public class MyLinkedList<T> implements MyList<T> {

    /**
     * Static nested class that presents a node in the linked list; also generic.
     */
    private static class MyNode<U> {
        public U value;                // Value at node
        public MyNode<U> prev, next;   // "Pointer" to the next node
    }

    private int length;
    private MyNode<T> head, tail;

    /* Trivial constructor */
    public MyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    /* Constructor that takes in a vararg of values to populate; O(n) */
    public MyLinkedList(T... values) {
        for (T val : values) {
            append(val);
        }
    }

    public int getLength() {
        return length;
    }

    /* Append an element to the end of the linked list; O(1) */
    public void append(T value) {
        length++;

        MyNode<T> newNode = new MyNode<>();
        newNode.value = value;
        newNode.next = null;
        newNode.prev = tail;

        if (tail != null) {         // non-empty linked list
            tail.next = newNode;
            tail = newNode;
        } else {                    // empty linked list
            head = newNode;
            tail = newNode;
        }
    }

    /* Insert an element to the linked list, such that the value will appear at index idx; worst case O(n) */
    public void insert(int idx, T value) {
        if (idx < 0 || idx > length) {
            throw new IndexOutOfBoundsException("insert() out of bound for idx = " + idx);
        }

        length++;
        MyNode<T> newNode = new MyNode<T>();
        newNode.value = value;
        newNode.prev = null;
        newNode.next = null;

        if (length == 1) {              // special case for inserting to empty list
            head = newNode;
            tail = newNode;
            return;
        }
        if (idx == 0) {                 // special case for inserting to head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        if (idx == length - 1) {        // special case for inserting to tail
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            return;
        }

        // General case for inserting to interior node
        // Visualization (y is to be inserted): head - .... - x - *y* - z - ... - tail
        // Move pointer to right before insertion point
        MyNode<T> node = head;
        for (int i = 0; i < idx - 1; i++) {
            node = node.next;
        }

        // Update surrounding pointers
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
    }

    /* Remove the node at index idx */
    public void remove(int idx) {
        if (idx < 0 || idx >= length) {
            throw new IndexOutOfBoundsException("remove() out of bound for idx = " + idx);
        }

        length--;

        // Visualization (y is to be removed): head - .... - x - y - z - ... - tail
        // Move pointer to the node to be removed
        MyNode<T> node = head;
        for (int i = 0; i < idx; i++) {
            node = node.next;
        }

        // Modify existing pointers in different cases
        // Note that any references to the soon-to-be removed MyNode are erased, such that Java GC and recycle memory
        if (node.prev != null && node.next != null) {           // interior node case
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else if (node.prev == null && node.next == null) {    // singleton case
            head = null;
            tail = null;
        } else if (node.prev == null) {                         // head case
            head.next.prev = null;
            head = head.next;
        } else {                                                // tail case
            tail.prev.next = null;
            tail = tail.prev;
        }
    }

    /* Get the head node value in the linked list */
    public T getHeadValue() {
        if (head == null) {
            throw new IllegalStateException("getHeadValue() for empty linked list!");
        }
        return head.value;
    }

    /* Get the tail node value in the linked list */
    public T getTailValue() {
        if (tail == null) {
            throw new IllegalStateException("getTailValue() for empty linked list!");
        }
        return tail.value;
    }

    /* Creates a new linked list by mapping each of its elements */
    public <U> MyList<U> map(MapperFn<T,U> fn) {
        MyLinkedList<U> newList = new MyLinkedList<>();
        for (T elem : this) {
            newList.append(fn.call(elem));
        }
        return newList;
    }

    /* Get an iterator for the current state of the linked list */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /* Private pointer for the value to be returned next */
            private MyNode<T> current = head;   // shorthand for MyLinkedList.this.head

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }
        };
    }

    /* Get an iterator for the current state of the linked list in reverse order */
    public Iterator<T> iteratorReversed() {
        return new Iterator<T>() {
            private MyNode<T> current = tail;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                T result = current.value;
                current = current.prev;
                return result;
            }
        };
    }

    /* Visualize the linked list for debugging purposes */
    public String toString() {
        if (length == 0) {
            return "MyLinkedList<T>(empty)";
        }

        StringBuilder forwardSb = new StringBuilder("|");
        iterator().forEachRemaining(elem -> { forwardSb.append(elem); forwardSb.append("|"); } );
        StringBuilder backwardSb = new StringBuilder("|");
        iteratorReversed().forEachRemaining(elem -> { backwardSb.append(elem); backwardSb.append("|"); } );

        String result = String.format("MyLinkedList<T>(length = %d):%n" +
            "head = %s, tail = %s%n" + "forwardIter = %s, backwardIter = %s",
                length, head.value, tail.value, forwardSb.toString(), backwardSb.toString());

        return result;
    }
}