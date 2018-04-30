package section21;

import java.util.Iterator;

/**
 * This class includes comprehensive tests for the doubly-linked list in section21.MyLinkedList.
 * All core methods in MyLinkedList<T> are covered by the following tests.
 */
public class MyLinkedListTest {

    public static void main(String[] argv) {
        /* Test empty linked list */
        System.out.println("\nTesting empty list...");
        MyLinkedList<String> emptyList = new MyLinkedList<>();
        System.out.println(emptyList);

        /* Test pre-populated linked list (hence append) */
        System.out.println("\nTesting pre-populated list...");
        MyLinkedList<String> populatedList = new MyLinkedList<>("foo", "bar", "baz");
        System.out.println(populatedList);

        /* Test repeated insertions */
        System.out.println("\nTesting repeated insertions...");
        populatedList.insert(0, "first");
        System.out.println(populatedList);
        populatedList.insert(2, "interior");
        System.out.println(populatedList);
        populatedList.insert(5, "tail");
        System.out.println(populatedList);

        /* Test insertion into empty list */
        emptyList.insert(0, "singleton");
        System.out.println(emptyList);

        /* Test repeated removes */
        System.out.println("\nTestin repeated removals...");
        populatedList.remove(0);
        System.out.println(populatedList);
        populatedList.remove(4);
        System.out.println(populatedList);
        populatedList.remove(2);
        System.out.println(populatedList);

        emptyList.remove(0);
        System.out.println(emptyList);
    }
}
