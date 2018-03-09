package section9;

/**
 * This class demonstrates the effects of method overloading.
 * In short, the most "specific" signature wins; ambiguity is illegal.
 * Created by jzong on 3/6/18.
 */
public class MethodOverloading {
    /* All following method have method name n, but with different signatures */
    double m(int n) {
        return n;
    }

    boolean m(boolean b) {
        return !b;
    }

    static double m(int x, double y) {
        return x + y + 1;
    }

    /* Cannot have the following method, otherwise calling m(12, 12) would be illegal (since it's ambiguous)!
    static double m(double x, int y) {
        return x + y + 1;
    }
    */

    static double m(double x, double y) {
        return x + y + 3;
    }

    public static void main(String[] argv) {
        // Reminder: int is subtype of double
        System.out.format("m(10, 20)=%f%n", m(10, 20));         // 31.0 -- most specific signature is m(int, double)
        System.out.format("m(10.0, 20)=%f%n", m(10.0, 20));     // 33.0 -- most specific signature is m(double, double)
        System.out.format("m(10, 20.0)=%f%n", m(10, 20.0));     // 31.0 -- most specific signature is m(int, double)
        System.out.format("m(10.0, 20.0)=%f%n", m(10.0, 20.0)); // 33.0 -- most specific signature is m(double, double)
    }
}
