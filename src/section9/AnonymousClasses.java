package section9;

import java.util.Iterator;

/**
 * This class demonstrates the use of anonymous classes.
 * The two examples are equivalent, where one returns anonymous class, and the others defines the class explicitly.
 * Created by jzong on 3/9/18.
 */
public class AnonymousClasses {
    /* Given string s, return an iterator that contains all suffixes of s */
    static Iterator<String> suffixes(final String s) {
        // Instantiate a class after implementing the interface (Iterator)
        return new Iterator<String>() {
            int startIndex = 0;
            /* These are the override methods */
            public boolean hasNext() { return startIndex < s.length(); }
            public String next() { return s.substring(startIndex++); }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }

    /* Explicit definition of SuffixIterator class that implements Iterator<String> interface */
    static Iterator<String> suffixesExplicit(final String s) {
        // Define a non-static local class; "static" modifier cannot be used here.
        class SuffixIterator implements Iterator<String> {
            int startIdx = 0;

            /* These are override methods; same as above */
            public boolean hasNext() {
                return startIdx < s.length();
            }

            public String next() {
                return s.substring(startIdx++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        // Return an instantiation of the class
        return new SuffixIterator();
    }

    public static void main(String[] argv) {
        Iterator<String> foobarSuffixes = suffixes("foobar");
        foobarSuffixes.forEachRemaining(s -> System.out.println(s));

        Iterator<String> explicitSuffixes = suffixesExplicit("ymzong");
        explicitSuffixes.forEachRemaining(s -> System.out.println(s));
    }
}
