package section22;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * This example demonstrates key methods exposed by ListIterator<T> interface in addition to Iterator<T>.
 */
public class ListIteratorMethods {

    public static void main(String[] argv) {
        List<String> list = new ArrayList<>();
        list.add("foo");
        list.add("bar");
        list.add("baz");

        // List#listIterator: Obtain listIterator from List
        ListIterator<String> iter = list.listIterator();

        // nextIndex(), previousIndex(): Print out the index of value to be returned by next() and prev()
        System.out.println(iter.nextIndex());
        System.out.println(iter.previousIndex());   // warning: this returns -1, i.e. out of bounds

        // next(), previous(), hasNext(), hasPrevious()
        System.out.println(iter.hasNext());
        System.out.println(iter.next());        // print out current value and move pointer forward
        System.out.println(iter.hasPrevious());
        System.out.println(iter.previous());    // print out value before pointer and move pointer backward

        // add(): adds value right before the pointer location
        System.out.println(iter.next());        // pointer is now at "bar"
        iter.add("unicorn");                    // "unicorn" added right before pointer (i.e. "bar")
        System.out.println(list);

        // remove(): removes value at the pointer location and moves on (also available for Iterator)
        iter.next();                            // iter now at "bar"
        iter.remove();                          // "bar" is removed; pointer moves to "baz"
        System.out.println(iter.nextIndex());
        System.out.println(list);
    }

}
