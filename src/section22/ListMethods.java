package section22;

import java.lang.reflect.Field;
import java.util.*;

/**
 * This example demonstrates key methods exposed by List<T> interface, as well as extra methods implemented by
 * ArrayList and LinkedList.
 */
public class ListMethods {

    /* Invokes most common List methods */
    public static void listMethods() {
        List<String> list = new ArrayList<>();

        // add, addAll (both now allow index specification)
        list.add("foo");
        list.add(0, "bar");
        print(list);
        list.addAll(1, list);
        print(list);

        // equals: element-wise equals() comparison
        List<String> anotherList = new ArrayList<>();
        anotherList.add("bar");
        anotherList.add("bar");
        anotherList.add("foo");
        anotherList.add("foo");
        System.out.println(list.equals(anotherList));

        // get
        System.out.println(list.get(2));

        // indexOf, lastIndexOf
        System.out.println(list.indexOf("foo"));
        System.out.println(list.lastIndexOf("foo"));
        System.out.println(list.indexOf("42"));

        // listIterator: returns ListIterator of the List (more on section22.ListIteratorMethods)
        ListIterator<String> listIterator = list.listIterator();    // (iterator poingint at 0)
        System.out.println(listIterator.next());                    // bar (iterator pointing at idx 1)
        System.out.println(listIterator.next());                    // bar (iterator pointing at idx 2)
        System.out.println(listIterator.next());                    // foo (iterator pointing at idx 3)
        listIterator.add("middle");                                 // insert between idx 2 and 3
        print(list);                                                // bar bar foo middle foo

        // remove
        System.out.println(list.remove(3));
        print(list);

        // set
        list.set(0, "head");
        print(list);

        // subList
        print(list.subList(2, 4));
    }

    /* Invokes special methods available to LinkedList (vs. List) */
    public static void linkedListMethods() {
        System.out.println();

        // Can initialize LinkedList from an existing Collection
        LinkedList<String> linkedList = new LinkedList<>(Collections.singleton("head"));
        linkedList.add("middle");
        linkedList.add("tail");

        // Since LinkedList is actually doubly-linked list, it supports O(1) get/add/remove methods on both ends
        linkedList.getFirst();
        linkedList.peekFirst();
        linkedList.addFirst("newHead");
        print(linkedList);
        linkedList.removeFirst();
        print(linkedList);
    }

    /* Demonstrates the internals of ArrayList resizing when out of capacity. (Spoiler: 1.5x in size) */
    public static void arrayListMethods() {
        System.out.println();

        // Can initialize ArrayList from an existing Collection
        ArrayList<String> arrayList = new ArrayList<>(Collections.nCopies(12, "Jimmy"));
        printArrayListCapacity(arrayList);

        // ArrayList automatically expands 1.5x when out of capacity
        arrayList.add("foobar");
        printArrayListCapacity(arrayList);
    }

    /**
     * Prints out the capacity (note: not size!) of an ArrayList using Java reflection.
     *
     * Could fail in the future if `elementData` field is renamed.
     */
    private static void printArrayListCapacity(ArrayList<?> arrayList) {
        try {
            Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            int capacity = ((Object[]) field.get(arrayList)).length;
            System.out.format("Current capacity: %d%n", capacity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Hacky reflection failed...");
        }
    }

    public static void main(String[] argv) {
        listMethods();
        linkedListMethods();
        arrayListMethods();
    }

    public static void print(List<String> list) {
        System.out.print("|");
        for (String elem : list) {
            System.out.print(elem + "\t");
        }
        System.out.println("|");
    }
}