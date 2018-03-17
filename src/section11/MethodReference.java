package section11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/**
 * This class demonstrates the use of method reference expressions while re-visiting lambda expressions.
 * Created by jzong on 3/14/18.
 */
public class MethodReference {
    // Can create flexible functions based on method reference
    private static Function<Integer, Character> makeHexConverter(boolean upper) {
        return (upper ? "0123456789ABCDEF" : "0123456789abcdef")::charAt;
    }

    static void methodRefExamples() {
        /* Use of different types of method references */
        // Reference to static method via t::m
        Function<String, Integer> parseInt = Integer::parseInt;                         // Integer.parseInt("12345")

        // Reference to instance method via t::m
        BiFunction<String, Integer, Character> charAt = String::charAt;                 // "12345".charAt(2)

        // Reference to instance method via e::m
        Function<Integer, Character> getHex = "0123456789ABCDEF"::charAt;               // "0123...".charAt(2)
        Consumer<String> println = System.out::println;                                 // System.out.println("foobar")

        // Reference to class constructor via C::new
        Function<Integer, C> createC = C::new;                                          // new C(19)
        Function<Integer, ArrayList<String>> makeStringList = ArrayList<String>::new;   // new ArrayList<String>(123)

        // Reference to array constructor via T[]::new
        // Note that T cannot be a generic type instance (e.g. ArrayList<String>)
        Function<Integer, Double[]> makeDoubleArray = Double[]::new;                    // new Double[123]

        // All method references except for T[]::new can take in optional type arguments
        // http://openjdk.java.net/jeps/101
        // https://stackoverflow.com/questions/49332873/use-of-type-parameter-in-java-method-reference
        BiConsumer<Double[], Comparator<Double>> sortDoubleArray = Arrays::<Double>sort;        // Arrays.sort(arr, cmp)

        // Use method reference created from a method
        Function<Integer, Character> hexConverterUpper = makeHexConverter(true);
        System.out.format("10 in hex is %c.%n", hexConverterUpper.apply(10));

        // Use this:: and super:: for method references
        // Reference of the object is also stored in the resulting Function object
        C c = new C(5);
        Supplier<Integer> getCValFromObject = c.getCVal();
        Supplier<Integer> getBValFromObject = c.getBVal();
        System.out.format("Value of val in C: %d%n", c.val);
        System.out.format("Value supplied by C.getCVal(): %d%n", getCValFromObject.get());  // 585 from C.getVal()
        System.out.format("Value supplied by C.getBVal(): %d%n", getBValFromObject.get());  // 5 from B.getVal()

        // Changes in field values are reflected
        c.val = 10;
        System.out.format("Value supplied by C.getCVal(): %d%n", getCValFromObject.get());  // 1170 from C.getVal()
        System.out.format("Value supplied by C.getBVal(): %d%n", getBValFromObject.get());  // 10 from B.getVal()
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

    // Can use this and super as object reference when building method reference
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