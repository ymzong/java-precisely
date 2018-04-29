package section21;

/**
 * This class implements a generic doubly-linked list with common operations.
 */
public class MyLinkedList<T> {

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

    /* Constructor that takes in a vararg of values to populate */
    public MyLinkedList(T... values) {
        for (T val : values) {
            append(val);
        }
    }

    /* Append an element to the end of the linked list */
    public void append(T value) {
        insert(length, value);
    }

    /* Insert an element to the linked list, such that the value will appear at index idx */
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
        if (idx == 0) {          // special case for inserting to head
            newNode.next = head;
            head = newNode;
            return;
        }
        if (idx == length-1) {   // special case for inserting to tail
            newNode.prev = tail;
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
}
