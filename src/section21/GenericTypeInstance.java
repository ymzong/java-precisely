package section21;

/**
 * This example shows a very simple generic class instance.
 */
public class GenericTypeInstance {

    public static void main(String[] argv) {
        Pair<String, Integer> p1 = new Pair<>("Java", 42);
        Pair<Double, Integer> p2 = new Pair<>(Math.PI, 1024);
        System.out.format("%s %d%n", p1.first, p1.second);
        System.out.format("%f %d%n", p2.first, p2.second);
    }
}

/* Very basic definition of a generic class */
class Pair<T, U> {
    public final T first;
    public final U second;

    public Pair(T t, U u) {
        first = t;
        second = u;
    }
}