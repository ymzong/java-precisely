package section23;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class demonstrates the use of various functional interfaces.
 */
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

        // Wildcard types should be used for accommodating method signature
        Stream<String> strings = Stream.of("Basic", "Pascal", "C++", "Python");
        Function<String, Integer> fn1 = s -> s.length();
        Function<Number, String> fn2 = n -> n.toString();
        // mapStream(strings, fn1, fn2);    <-- this gives compilation error since types don't exactly match
        print(mapStreamWildcard(strings, fn1, fn2).collect(Collectors.toList()));

        // UnaryOperator<T> is pretty much shorthand for Function<T,T>
        UnaryOperator<String> repeatStringUnary = s -> s + s;
        print(repeatStringUnary.apply("tacocat️"));
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

    public static void main(String[] argv) {
        functionExamples();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
