package section23;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class FunctionalInterfaces {

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
    }

    public static void main(String[] argv) {
        functionExamples();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
