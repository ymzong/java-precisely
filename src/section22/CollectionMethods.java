package section22;

import java.util.*;
import java.util.stream.Stream;

/**
 * This example demonstrates key methods exposed by Collection<T> interface, which covers Lists and Sets.
 */
public class CollectionMethods {

    public static void main(String[] argv) {
        Collection<String> sample = new HashSet<>();

        // add, addAll
        sample.add("foo");
        sample.addAll(Collections.singleton("bar"));
        print(sample);

        // clear, isEmpty
        sample.clear();
        System.out.println(sample.isEmpty());

        // contains, containsAll
        sample.add("foo");
        sample.add("bar");
        System.out.println(sample.contains("foo"));
        System.out.println(sample.containsAll(Collections.singleton("baz")));

        // iterator
        Iterator<String> iter = sample.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // remove, removeAll, removeIf
        sample.add("fooooo");
        sample.add("barrrr");
        sample.add("");
        sample.remove("");
        print(sample);
        sample.removeAll(Collections.singleton("foo"));
        print(sample);
        sample.removeIf(elem -> elem.length() > 3);
        print(sample);

        // retainAll
        sample.retainAll(Collections.singleton("nothing"));
        print(sample);

        // size
        System.out.println(sample.size());

        // stream, parallelStream
        sample.add("foo");
        sample.add("bar");
        Stream<String> stream = sample.stream();    // same for parallelStream, which could be parallel
        stream.map(elem -> "hey " + elem).forEach(elem -> System.out.print(elem + "\t"));
        System.out.println();

        // toArray
        Object[] array = sample.toArray();          // type erasure of generic type => we can only get Object[] :(
        String[] stringArray = sample.toArray(new String[sample.size()]);   // better: feed in a typed array
        System.out.println(Arrays.toString(stringArray));
    }

    public static void print(Collection<String> collection) {
        System.out.print("|");
        for (String elem : collection) {
            System.out.print(elem + "\t");
        }
        System.out.println("|");
    }

}
