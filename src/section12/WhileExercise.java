package section12;

import java.util.Arrays;

/**
 * This class implements some (un)common uses of while loop.
 * Created by jzong on 3/21/18.
 */
public class WhileExercise {

    public static int linearSearchString(String val, String[] values) {
        int i = 0;
        while (i < values.length && !values[i].equals(val)) {   // Use short-circuit to guard array index
            i++;
        }
        return (i < values.length) ? i : -1;    // Depending on which condition caused while loop to exit: found or not
    }

    /* This method implements a slight variant of Java's Arrays.binarySearch
     * The only difference is the following: when val exists in values array, return the index of
     * the _first_ occurrence of val, instead of any index. */
    public static int binarySearchString(String val, String[] values) {
        if (values.length == 0) {   // trivial case
            return -1;
        }

        int start = 0;
        int end = values.length - 1;

        while (start < end) {   // more than one number in range
            int mid = start + (end - start) / 2;
            // Aggressively shave off latter half, since we want to find the first match (if exists)
            if (val.compareTo(values[mid]) <= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        // Here, start == end, and val either sits at the index, or should appear right before or after it
        if (values[start].equals(val)) {
            return start;
        } else if (values[start].compareTo(val) > 0){
            return ~(start);
        } else {
            return ~(start+1);
        }
    }

    public static void linearSearch() {
        String[] wdays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        System.out.println(linearSearchString("Monday", wdays));
        System.out.println(linearSearchString("Thursday", wdays));
        System.out.println(linearSearchString("Someday", wdays));
    }

    public static void binarySearch() {
        String[] easyCase = { "a", "b", "c", "d" };
        // Note: The following to be enabled with -ea flag
        assert(binarySearchString("a", easyCase) == 0);
        assert(binarySearchString("b", easyCase) == 1);
        assert(binarySearchString("c", easyCase) == 2);
        assert(binarySearchString("d", easyCase) == 3);
        assert(binarySearchString("ahh", easyCase) == -2);
        assert(binarySearchString("doh", easyCase) == -5);

        String[] tieCase = { "a", "b", "c", "c", "c", "c", "c", "c", "f" };
        assert(binarySearchString("a", tieCase) == 0);
        assert(binarySearchString("c", tieCase) == 2);
        assert(binarySearchString("ccc", tieCase) == -9);
        assert(binarySearchString("buzz", tieCase) == -3);
    }

    public static void main(String[] argv) {
        linearSearch();
        binarySearch();
    }
}
