package section18;

/**
 * This class demonstrates interesting behaviors of the static methods in Math library.
 */
public class MathFunctions {

    static void print(double x) {
        System.out.println(x);
    }

    static void print(String x) {
        System.out.println(x);
    }

    public static void main(String[] argv) {
        /* Illegal arguments usually gives NaN */
        print(Math.sqrt(-1));
        print(Math.log(-1));
        print(Math.pow(-1, 2.5));
        print(Math.acos(1.1));
        print("");

        /* Infinity return value */
        print(Math.exp(10000));
        print(Math.pow(1.0 / Double.POSITIVE_INFINITY, -1));    // base is positive-zero
        print(Math.pow(1.0 / Double.NEGATIVE_INFINITY, -1));    // base is negative-zero
        print(Math.log(0));
        print("");

        /* Infinity arguments lead to "expected" behavior */
        double pinf = Double.POSITIVE_INFINITY;
        double ninf = Double.NEGATIVE_INFINITY;
        print(Math.sqrt(pinf));
        print(Math.log(pinf));
        print(Math.exp(ninf));
        print("");

        /* Special cases and NaN */
        double nan = Double.NaN;
        print(Math.sqrt(nan));
        print(Math.pow(nan, 0));    // Math.pow(xxx, 0) always returns 1
        print(Math.pow(0, 0));      // Ditto
        print(Math.round(nan));     // Doesn't really make sense, but it has to return an int.
        print(Math.round(1E50));    // Returns the closest long, which is still not actually close
    }
}
