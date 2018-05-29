package section23;

/**
 * This class implements a generic immutable Linked List that demonstrates the advantages of immutable data structures.
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

    /* Prepends a value to the linked list, and returns a new immutable linked list */
    public ImmutableList<T> cons(T val) {
        Node<T> newHead = new Node<T>(val, head);
        ImmutableList<T> newList = new ImmutableList<>(newHead);
        return newList;
    }

    /* Utility method that returns the length of linked list */
    public int length() {
        int result = 0;
        Node<T> iter = head;

        while (iter != null) {
            result++;
            iter = iter.next;
        }
        return result;
    }

    /* Returns an element of the linked list by index */
    public T get(int idx) {
        if (idx < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        return getFromNode(head, idx);
    }

    /* Helper recursive method that returns the content of a node based on head and offset idx */
    private static <T> T getFromNode(Node<T> head, int idx) {
        if (head == null) {
            throw new IndexOutOfBoundsException("index overflow");
        }
        if (idx == 0) {
            return head.data;
        } else {
            return getFromNode(head.next, idx - 1);
        }
    }
}
