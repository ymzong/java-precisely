package section23;


import java.math.BigInteger;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class demonstrates the use of various functional interfaces.
 */
public class FunctionalInterfaces {

    // constant strings for convertNumberToEnglish
    private static final String[] ones = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen" };
    private static final String[] tens = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };


    static void functionExamples() {
        // All of the following parse a String into Integer
        Function<String, Integer>
                parse1 = s -> Integer.parseInt(s),          // lambda expression
                parse2 = (String s) -> Integer.parseInt(s), // lambda expression with explicit typing
                parse3 = Integer::parseInt,                 // method reference to Integer.parseInt(String s)
                parse4 = Integer::new;                      // method reference to constructor of Integer

        // All of the following get the length of String
        Function<String, Integer>
                len1 = s -> s.length(),                     // lambda expression
                len2 = String::length,                      // method reference to String#length
                len3 = new Function<String, Integer>() {    // explicit implementation of Function interface
                    public Integer apply(String s) {
                        return s.length();
                    }
                };

        // Function objects can be invoked with .apply()
        print(parse1.apply("123456"));
        print(len3.apply("foobar42"));

        // Functions may be chained with andThen and compose
        Function<String, String> repeatString = s -> s + s;
        print(parse2.compose(repeatString).apply("13579")); // invoke repeatString and then parse2

        Function<Integer, Integer> squaredValue = n -> n * n;
        print(len2.andThen(squaredValue).apply("java10"));  // invoke len2 and then squaredValue

        // Wildcard types should be used for accommodating method signature
        Stream<String> strings = Stream.of("Basic", "Pascal", "C++", "Python");
        Function<String, Integer> fn1 = s -> s.length();
        Function<Number, String> fn2 = n -> n.toString();
        // mapStream(strings, fn1, fn2);    <-- this gives compilation error since types don't exactly match
        print(mapStreamWildcard(strings, fn1, fn2).collect(Collectors.toList()));

        // UnaryOperator<T> is pretty much shorthand for Function<T,T>
        UnaryOperator<String> repeatStringUnary = s -> s + s;
        print(repeatStringUnary.apply("tacocat️"));

        // Predicate<T> and BiPredicate<T,U> are for Functions that map to boolean
        Predicate<String> isUpper = s -> s.equals(s.toUpperCase());
        BiPredicate<String, String> isEqualIgnoreCase = (s, t) -> s.toUpperCase().equals(t.toUpperCase());
        print(isUpper.test("GNARRRR"));
        print(isEqualIgnoreCase.test("mysql", "MySQL"));
    }

    /* The following two methods apply two functions to map every element in the stream */
    /* Bad example: non-wildcard type arguments are inflexible. */
    private static <T,U,V> Stream<V> mapStream(Stream<T> stream, Function<T,U> fn1, Function<U,V> fn2) {
        return stream.map(fn1.andThen(fn2));
    }

    /* Good example: By using wildcard types, this method is accommodating to broader input function types */
    private static <T,U,V> Stream<V> mapStreamWildcard(
            Stream<T> stream,
            Function<? super T, ? extends U> fn1,
            Function<? super U, ? extends V> fn2) {
        return stream.map(fn1.andThen(fn2));
    }


    /**
     * Concat function that handles "empty tokens" gracefully, i.e. if after is empty, simply return empty string.
     * (useful for example: 40 -> forty instead of "forty-")
     */
    private static String concatIfExists(String before, String after) {
        if (after.length() == 0) {
            return "";
        } else {
            return before + after;
        }
    }

    /* Converts a number between 0-100 to English (zero to empty string) */
    private static String convertLessThanHundredToEnglish(int n) {
        if (n < 20) {
            return ones[n];
        } else {
            return tens[n / 10 - 2] + concatIfExists("-", ones[n % 10]);
        }
    }

    /**
     * Converts only the segment of given number higher than digitOffset to English, and pass along the rest to
     * subConversion.
     *
     * Requirement: both upper and lower segments of the the provided number can be processed by subConversion
     */
    private static IntFunction<String> convertNumberSegment(
            String name, int digitOffset, IntFunction<String> subConversion) {
        return n -> {
            int upperSeg = n / (int)Math.pow(10, digitOffset);
            int lowerSeg = n % (int)Math.pow(10, digitOffset);
            if (upperSeg == 0) {                    // if upper segment is empty, we can delegate to lower segment
                return subConversion.apply(n);
            } else {                                // concat both segments (if lower is empty, leave out extra space)
                return subConversion.apply(upperSeg) + (" " + name) +
                        concatIfExists(" ", subConversion.apply(lowerSeg));
            }
        };
    }

    /**
     * Converts a positive number (up to MAX_INT) to English form.
     * The following cascade of functions use recursion, e.g. 2,481,632 can be processed by each comma-separated segment
     * We stop at 1 trillion because MAX_INT (2^31-1) is ~2 billion
     */
    private static final IntFunction<String>
        convertLess1K = convertNumberSegment("hundred", 2,
            FunctionalInterfaces::convertLessThanHundredToEnglish),
        convertLess1M = convertNumberSegment("thousand", 3, convertLess1K),
        convertLess1B = convertNumberSegment("million", 6, convertLess1M),
        convertLess1T = convertNumberSegment("billion", 9, convertLess1B);

    /**
     * User-ready method that converts an integer (entire range of Integer) to its English form.
     */
    public static String convertNumberToEnglish(int n) {
        if (n == 0) {
            return "zero";
        }

        String positiveNumber = convertLess1T.apply(Math.abs(n));
        return (n > 0) ? positiveNumber : "negative " + positiveNumber;
    }

    static void consumerSupplierExamples() {
        // Consumer<T> takes in single arg T and doesn't return anything
        Consumer<String> printString = System.out::println;
        Consumer<String> printFullVersion = s -> System.out.println("Java " + s);

        Stream<String> versions = Stream.of("8", "9", "10");
        versions.forEach(printString.andThen(printFullVersion));

        // Supplier<T> doesn't take in arguments and returns a T
        // Here, we use explicit instantiation to keep some private state in the Supplier (similar to Iterator)
        IntSupplier naturalNumberSupplier = new IntSupplier() {
            private int nextNumber = 0;     // keeps track of next number to return
            @Override
            public int getAsInt() {
                return (nextNumber++);
            }
        };

        // Another way to achieve the same is to keep the state externally
        final int[] nextNum = { 0 };                                        // to be updated by Supplier
        IntSupplier naturalNumberSupplier2 = () -> nextNum[0]++;

        // The example above can be modified to a Supplier of Fibonacci numbers
        final BigInteger[] nextFibs = { BigInteger.ZERO, BigInteger.ONE };  // to be updated by Supplier
        Supplier<BigInteger> fibonacciSupplier = () -> {
            BigInteger result = nextFibs[0];
            BigInteger nextFib = nextFibs[0].add(nextFibs[1]);
            nextFibs[0] = nextFibs[1];
            nextFibs[1] = nextFib;
            return result;
        };

        for (int i = 0; i < 10; i++) {
            print(naturalNumberSupplier.getAsInt());
            print(naturalNumberSupplier2.getAsInt());
            print(fibonacciSupplier.get());
        }
    }

    public static void main(String[] argv) {
        functionExamples();
        print(convertNumberToEnglish(-7744196));
        print(convertNumberToEnglish(Integer.MAX_VALUE));

        consumerSupplierExamples();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
