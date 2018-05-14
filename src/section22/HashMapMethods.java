package section22;

import java.util.*;

/**
 * This example demonstrates key methods exposed by Map<K,V> interface, as well as extra methods implemented by
 * HashMap<K,V>.
 */
public class HashMapMethods {

    public static void main(String[] argv) {
        Map<String, String> ssMap = new HashMap<>();

        // get, put, putAll, remove
        ssMap.put("Foo", "FooFoo");
        ssMap.put("Bar", "BarBar");
        System.out.println(ssMap.get("Foo"));

        Map<String, String> anotherMap = new HashMap<>();
        anotherMap.put("Baz", "BazBaz");
        anotherMap.put("Foo", "NewValue");
        ssMap.putAll(anotherMap);
        System.out.println(ssMap);

        ssMap.remove("Baz");
        ssMap.remove("NonExistent");    // no-op

        // clear
        anotherMap.clear();
        System.out.println(anotherMap);

        // containsKey, containsValue
        System.out.println(ssMap.containsKey("Foo"));
        System.out.println(ssMap.containsValue("NewValue"));

        // entrySet (giving Set of HashMap.Entry)
        Set<HashMap.Entry<String, String>> entries = ssMap.entrySet();
        for (HashMap.Entry<String, String> kv : entries) {
            System.out.format("%s\t%s%n", kv.getKey(), kv.getValue());
        }

        // isEmpty, size
        System.out.println(ssMap.isEmpty());
        System.out.println(ssMap.size());

        // keySet, values
        System.out.println(ssMap.keySet());
        ssMap.put("Barr", "BarBar");
        System.out.println(ssMap.values());     // note that Map.values() can have dupes

        // Initialize HashMap
        HashMap<String, String> emptyHashMap = new HashMap<>();
        HashMap<String, String> populatedHashMap = new HashMap<>(ssMap);
        System.out.println(ssMap.equals(populatedHashMap));

        // LinkedHashMap: guarantees traversal by key insertion order (as opposed to arbitrary order by HashMap)
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("foo", "bar");
        linkedHashMap.put("bar", "baz");
        linkedHashMap.put("baz", "foo");
        System.out.println(linkedHashMap);
        System.out.println(new HashMap<>(linkedHashMap));

        // IdentityHashMap: changes overall equality-testing rule from using equals() to using ==
        // Advantage: better memory footprint at large cardinality
        IdentityHashMap<Integer, String> idHashMap = new IdentityHashMap<>();
        idHashMap.put(new Integer(1), "One");
        idHashMap.put(Integer.valueOf(1), "One");
        System.out.println(idHashMap.size());
        System.out.println(idHashMap);
    }
}