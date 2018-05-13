package section22;

import java.util.*;

/**
 * This example demonstrates the way Set<T> tests for equality between elements, as well as extra methods exposed by
 * SortedSet<T> (and its TreeSet<T> implementation).
 */
public class SetMethods {

    /**
     * The following section shows that Set<T> uses .equals() method element-wise to test for equality, both when adding
     * elements and when testing for equality/superset between two sets.
     */
    public static void setEquality() {
        Set<Integer> set = new HashSet<>();
        Integer i1 = Integer.valueOf(0);
        Integer i2 = new Integer(0);
        System.out.println(i1 == i2);                           // two different Integer objects
        System.out.println(i1.equals(i2));                      // ... of equal values (zero)

        set.add(i1);
        set.add(i2);
        System.out.format("Size of set: %d%n", set.size());     // Set uses .equals() to test for equality

        Set<Integer> set1 = Collections.singleton(i1);
        Set<Integer> set2 = Collections.singleton(i2);
        System.out.println(set1 == set2);                       // two different set objects
        System.out.println(set1.equals(set2));                  // ... that are equal (both contain one "zero" Integer)
        System.out.println(set1.containsAll(set2));             // ditto
    }

    /**
     * The following section shows additional methods from SortedSet<T> and TreeSet<T>. Note that the latter is
     * implemented with a self-balanced BST (Red-Black Tree) in Java.
     */
    public static void sortedSet() {
        SortedSet<String> sset = new TreeSet<>();
        List<String> months =  Arrays.asList("January", "February", "March", "April", "May");
        sset.addAll(months);

        // first, last
        System.out.println(sset.first());
        System.out.println(sset.last());

        // comparator
        System.out.println(sset.comparator());  // returns null since we use the native Comparator of String

        // headSet, tailSet, subSet
        Set<String> head = sset.headSet("G");   // generate a partial view of the set for anything < G
        System.out.println(head);
        head.add("December");
        // head.add("June");    <-- throws exception since "June" >= "G"
        System.out.println(sset);

        // TreeSet constructors: can start with existing TreeSet/Collection, or specify a Comparator
        TreeSet<String> emptySet = new TreeSet<>();
        TreeSet<String> copySet = new TreeSet<>(head);
        TreeSet<String> setFromCollection = new TreeSet<>(months);

        // Supply a custom String comparator that ignores case
        TreeSet<String> setWithComparator = new TreeSet<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        setWithComparator.add("Foo");
        setWithComparator.add("bar");
        System.out.println(setWithComparator);  // "bar" goes before "Foo" since comparator ignores case
    }

    public static void main(String[] argv) {
        setEquality();
        sortedSet();
    }

}