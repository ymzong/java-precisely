package section11;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class demonstrates the use of method reference expressions while re-visiting lambda expressions.
 * Created by jzong on 3/14/18.
 */
public class MethodReference {
    static void methodRefExamples() {
        /* Use of different types of method references */
        // Reference to static method via t::m
        Function<String, Integer> parseInt = Integer::parseInt;             // Integer.parseInt("12345")
        // Reference to instance method via t::m
        BiFunction<String, Integer, Character> charAt = String::charAt;     // "12345".charAt(2)

        // Reference to instance method via e::m
        Function<Integer, Character> getHex = "0123456789ABCDEF"::charAt;   // "0123...".charAt(2)

        // Reference to class constructor via C::new
        Function<Integer, C> createC = C::new;                              // C.new(19)

        // Reference to array constructor via T[]::new
        Function<Integer, Double[]> makeDoubleArray = Double[]::new;        // new Double[123]

        
    }

    public static void main(String[] argv) {
        methodRefExamples();
    }
}

class B {
    protected int val;

    public int getVal() {
        return val;
    }
}

class C extends B {
    public C(int val) {
        this.val = val;
    }

    public Supplier<Integer> getBVal() {
        return super::getVal;
    }

    public Supplier<Integer> getCVal() {
        return this::getVal;
    }

    public int getVal() {
        return 117 * val;
    }
}