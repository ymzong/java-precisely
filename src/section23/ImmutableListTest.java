package section23;

import static section23.ImmutableList.cons;

/**
 * This class includes comprehensive tests for the immutable linked list in section23.ImmutableList.
 * All core methods in ImmutableList are covered by the following tests.
 */
public class ImmutableListTest {

    public static void main(String[] argv) {
        ImmutableList<String> list = new ImmutableList<>();
        ImmutableList<String> newList = cons("foo", cons("bar", list));
        print(list);                                        // `list` is still empty (immutable)
        print(newList);
        print(newList.length());
        print(newList.get(1));

        ImmutableList<String> mappedList = newList.map(x -> x + x);
        print(mappedList);
        ImmutableList<String> insertedList = mappedList.insert("bazbaz", 1);
        print(insertedList);

        ImmutableList<String> revertedList = insertedList.revert();
        print(revertedList);
    }

    private static void print(Object o) {
        System.out.println(o);
    }

}
