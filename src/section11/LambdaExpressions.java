package section11;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class demonstrates the use of Lambda functions.
 * Created by jzong on 3/14/18.
 */
public class LambdaExpressions {
    static void basicLambdaExpressions() {
        /* Defining lots of lambda functions for use later: Function, BiFunction, Supplier, Consumer */
        // Function<T1, T2> takes in T1 and returns T2
        // The following four functions are equivalent -- String to Integer parser
        Function<String, Integer>
                parseInt1 = s -> Integer.parseInt(s),
                parseInt2 = s -> { return Integer.parseInt(s); },
                parseInt3 = (String s) -> Integer.parseInt(s),
                parseInt4 = (final String s) -> { return Integer.parseInt(s); };

        // The following three functions are equivalent -- concating string arrays
        Function<String[], String>
                arrayConcat1 = ss -> String.join(",", ss),
                arrayConcat2 = (String[] ss) -> String.join(",", ss),
                arrayConcat3 = (String... ss) -> String.join(",", ss);

        // BiFunction<T1, T2, T3> takes in (T1,T2) and returns T3
        // The following two functions are equivalent -- returns 3-char substring starting from given index
        BiFunction<String, Integer, String>
                substr1 = (s, idx) -> s.substring(idx, Math.min(idx+3, s.length())),
                substr2 = (s, idx) -> {
                    int toIdx = Math.min(idx+3, s.length());
                    return s.substring(idx, toIdx);
                };

        // Simple string concatenation
        BiFunction<String, String, String>
                concat = (s1, s2) -> s1 + s2;

        // Supplier<T1> takes in nothing and returns T1
        Supplier<String>
                now = () -> new java.util.Date().toString();

        // Consumer<T1> takes in T1 and returns void
        Consumer<String>
                display1 = s -> System.out.println(">>> " + s + " <<<"),
                display2 = s -> { System.out.println(">>> " + s + " <<<"); };

        /* Calling lambda functions above */
        System.out.println(parseInt1.apply("12345"));
        System.out.println(substr2.apply("foobar", 3)); // automatic boxing int -> Integer
        System.out.println(concat.apply("foo", "bar"));
        System.out.println(now.get());                  // call Supplier with get()
        display2.accept("lambda expressions");          // call Consumer with accept()

        /* Type of functional interface determines what parameters can be passed in */
        System.out.println(arrayConcat3.apply(new String[] { "a", "b", "c" }));
        // The following is illegal because the type of arrayConcat3 is Function<String[], String>
        // String illegal = arrayConcat3.apply("a", "b", "c");
    }

    static void higherOrderLambda() {
        /* Lambdas can also appear in argument type and return type, thereby creating higher-order lambdas */
        // Curried string concat function
        Function<String, Function<String, String>>
                prefix = s1 -> s2 -> s1 + s2;
        Function<String, String> dollarPrefix = prefix.apply("$");
        System.out.println(prefix.apply("$").apply("1024.00"));
        System.out.println(dollarPrefix.apply("1024.00"));

        // Apply the given function twice to the string
        BiFunction<Function<String, String>, String, String>
                applyTwice = (fn, s) -> fn.apply(fn.apply(s));
        Function<String, String>
                doubleDollarPrefix = s -> applyTwice.apply(dollarPrefix, s);
        System.out.println(applyTwice.apply(dollarPrefix, "1024.00"));
        System.out.println(doubleDollarPrefix.apply("1024.00"));
    }

    public static void main(String[] argv) {
        basicLambdaExpressions();
        higherOrderLambda();
    }
}
