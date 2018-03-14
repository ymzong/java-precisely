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
    public static void main(String[] argv) {
        /* Defining lots of Lambda functions for use later */
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
    }
}
