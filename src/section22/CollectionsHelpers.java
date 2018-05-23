package section22;

import java.util.*;
import java.util.function.Consumer;

/**
 * This example covers helper methods offered by Collections library.
 */
public class CollectionsHelpers {

    final static String[] RESERVED_WORDS = { "abstract", "assert", "boolean", "break", "byte", "case", "catch" };

    public static void main(String[] argv) {
        List<String> reservedWords = Arrays.asList(RESERVED_WORDS);
        Consumer<Object> print = o -> System.out.println(o);        // shorthand for System.out.println()

        // binarySearch: take in a list and value, optionally accept a Comparator
        print.accept(Collections.binarySearch(reservedWords, "import",
                Comparator.naturalOrder()   // unnecessary
        ));

        // copy: perform a *shallow* copy of list
        List<StringBuilder> original = Arrays.asList(new StringBuilder("foo"), new StringBuilder("bar"));
        List<StringBuilder> shallowCopy = Arrays.asList(null, null, null);
        Collections.copy(shallowCopy, original);
        shallowCopy.get(0).append("(written from shallow copy)");   // modify StringBuffer from shallowCopy
        print.accept(original);                                     // original value also affected

        // enumeration: create an Enumeration of a list (read-only Iterator)
        Enumeration<String> listEnum = Collections.enumeration(reservedWords);
        print.accept(listEnum.nextElement());

        // fill: fill a list with given value
        Collections.fill(original, null);
        print.accept(original);

        // min, max: optionally accept a Comparator
        print.accept(Collections.max(reservedWords));
        print.accept(Collections.min(reservedWords, Comparator.<String>reverseOrder()));

        // nCopies: read-only list with n copies of certain value
        List<String> cats = Collections.nCopies(9, "meow");
        print.accept(cats);

        // replaceAll, reverse
        List<String> originalStrings = Arrays.asList("foo", "bar", "baz", "foo");
        Collections.replaceAll(originalStrings, "foo", "java");
        print.accept(originalStrings);
        Collections.reverse(originalStrings);
        print.accept(originalStrings);

        // reverseOrder: get the reverse order of the native/given Comparator for the elements (e.g. to feed into sort)
        Collections.sort(reservedWords, Collections.reverseOrder());
        print.accept(reservedWords);

        // rotate: rotate list towards the *right* by certain spots
        List<String> coffee = Arrays.asList("Blue Bottle", "For Five", "Kobrick");
        Collections.rotate(coffee, 2);
        print.accept(coffee);

        // shuffle: randomly permutes all elements of a list
        Collections.shuffle(reservedWords);
        print.accept(reservedWords);

        // singleton, singletonList, singletonMap: all read-only
        print.accept(Collections.singleton("singleton_set"));
        print.accept(Collections.singletonList("singleton_list"));
        print.accept(Collections.singletonMap("favoriteLanguage", "Java"));

        // sort: sort list with natural/provided Comparator
        Collections.sort(reservedWords, Comparator.comparing(s -> s.charAt(1)));
        print.accept(reservedWords);

        // swap: swap two indeces in the list
        Collections.swap(reservedWords, 0, 1);
        print.accept(reservedWords);

        // {synchronized,unmodifiable}*: create synchronized (thread-safe not concurrent) / read-only view of Collection
        List<String> syncedList = Collections.synchronizedList(reservedWords);
        List<String> readOnlyList = Collections.unmodifiableList(reservedWords);
    }

}
