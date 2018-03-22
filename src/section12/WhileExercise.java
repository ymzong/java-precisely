package section12;

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

    public static void linearSearch() {
        String[] wdays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        System.out.println(linearSearchString("Monday", wdays));
        System.out.println(linearSearchString("Thursday", wdays));
        System.out.println(linearSearchString("Someday", wdays));
    }

    public static void main(String[] argv) {
        linearSearch();
        // binarySearch();
    }
}
