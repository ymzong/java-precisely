package section21;

/**
 * This example uses ComparableTuple declared earlier.
 */
public class ComparableTupleTest {

    public static void main(String[] argv) {
        ComparableTuple<String, Integer> jimmy = new ComparableTuple<>("Jimmy", 11);
        ComparableTuple<String, Integer> jimmy2 = new ComparableTuple<>("Jimmy", 19);
        ComparableTuple<String, Integer> peter = new ComparableTuple<>("Peter", 2);

        System.out.println(jimmy.compareTo(jimmy2));    // 11 < 19
        System.out.println(jimmy.compareTo(peter));     // "Jimmy" < "Peter"
    }
}
