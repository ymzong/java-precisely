package section22;

import java.util.*;

/**
 * This example shows basic instantiation and iteration of Java Collection / Map classes.
 *
 * It also demonstrates the guaranteed key (value) traversal order (if any) provided by the data structures.
 */
public class CreateCollectionsMaps {

    /**
     * Lists keep all inserted values; Sets keep distinct values only.
     *
     * - ArrayList, LinkedList guarantee traversal in insertion order;
     *
     * - TreeSet guarantees traversal in native value order;
     * - LinkedHashSet guarantees traversal in value insertion order;
     * - HashSet offers no order guarantees.
     */
    public static void collections() {
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        populateCollection(arrayList);
        populateCollection(linkedList);
        populateCollection(hashSet);
        populateCollection(linkedHashSet);
        populateCollection(treeSet);

        traverse(arrayList);
        traverse(linkedList);
        traverse(hashSet);
        traverse(linkedHashSet);
        traverse(treeSet);
    }

    private static void populateCollection(Collection<String> collection) {
        collection.add("foo");
        collection.add("bar");
        collection.add("42");
        collection.add("bar");
    }

    /**
     * Common properties among maps: duplicate keys are dropped in favor of newest insertion.
     *
     * - LinkedHashMap guarantees traversal in key insertion order;
     * - TreeMap guarantees traversal in native key order;
     * - HashMap offers no order guarantees.
     */
    public static void maps() {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        populateMap(hashMap);
        populateMap(linkedHashMap);
        populateMap(treeMap);

        traverse(hashMap.keySet());
        traverse(hashMap.values());
        traverse(linkedHashMap.keySet());
        traverse(linkedHashMap.values());
        traverse(treeMap.keySet());
        traverse(treeMap.values());
    }

    private static void populateMap(Map<String,String> map) {
        map.put("map", "J");
        map.put("dup", "K");
        map.put("x", "M");
        map.put("dup", "L");
    }

    private static void traverse(Collection<String> strings) {
        for (String s : strings) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }

    public static void main(String[] argv) {
        collections();
        System.out.println("---------------");
        maps();
    }
}