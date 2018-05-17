package section22;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This examples shows some common cases of Iterables along with their usage.
 */
public class IterableExamples {

    // Since Collection<T> implements Iterable<T>, all Collections are inherently Iterable.
    public static void collectionIterable() {
        Collection<String> coll = new ArrayList<>();
        coll.add("123");
        coll.add("456");
        coll.add("789");

        for (String elem : coll) {                  // use enhanced for-loop to go over inherent iterator
            System.out.print(elem + "\t");
        }
        System.out.println();

        Iterator<String> iter = coll.iterator();    // use iterator() to get explicit Iterator from Iterable
        while (iter.hasNext()) {
            System.out.print(iter.next() + "\t");
            iter.remove();                          // remove() is the only legal way to modify collection in iteration
        }
        System.out.println("\n" + coll.size());
    }

    // Map exposes method entrySet, which gives a Set of Map.Entry contained in the Map
    public static void mapIterable() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "foofoo");
        map.put("bar", "barbar");

        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
            System.out.format("%s\t%s\n", mapEntry.getKey(), mapEntry.getValue());
        }
    }

    // The following method generates and returns an Iterable between two integers (using local classes)
    public static Iterable<Integer> fromTo(int from, int to) {

        // First, create an Iterator based on any range
        class RangeIterator implements Iterator<Integer> {
            private int nextValue;
            private final int fromValue, toValue;

            public RangeIterator(int from, int to) {
                fromValue = from;
                toValue = to;
                nextValue = from;
            }

            @Override
            public boolean hasNext() {
                return nextValue < to;
            }

            @Override
            public Integer next() {
                if (nextValue < to) {
                    return (nextValue++);
                } else {
                    throw new NoSuchElementException();
                }
            }
        }

        // Then, create an Iterable whose iterator() method initializes a RangeIterator defined above
        class RangeIterable implements Iterable<Integer> {

            private final int fromValue, toValue;

            public RangeIterable(int from, int to) {
                fromValue = from;
                toValue = to;
            }

            @Override
            public Iterator<Integer> iterator() {
                return new RangeIterator(fromValue, toValue);
            }
        }

        // Return the constructed Iterable
        return new RangeIterable(from, to);
    }

    public static void main(String[] argv) {
        collectionIterable();
        mapIterable();

        Iterable<Integer> iter = fromTo(32, 48);
        for (Integer i : iter) {
            System.out.print(i + "\t");
        }
        System.out.println();

        // Spoiler: fromTo can be done in one line with Stream
        iter = () -> IntStream.range(32, 48).iterator();
        for (int i : iter) {
            System.out.print(i + "\t");
        }
    }

}
