package section12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntUnaryOperator;

/**
 * This class demonstrates the use of "foreach" loop on Java Arrays and Iterables in general.
 * Created by jzong on 3/20/18.
 */
public class EnhancedForLoop {

    public static void foreachArray() {
        String[] langs = { "Java", "Python", "Bash" };

        // Foreach loop can be used to iterate an array
        for (String lang : langs) {
            System.out.println(lang);
        }
    }

    public static void foreachLambda() {
        Integer[] multipliers = {2, 3, 5, 7, 11, 13, 17, 19};
        Iterable<Integer> multipliersIter = Arrays.asList(multipliers); // Iterator of primes above

        List<IntUnaryOperator> fns = new ArrayList<>();       // To become list of multiplier functions
        for (Integer multiplier : multipliersIter) {
            fns.add(num -> num * multiplier);
        }

        // Test out the IntUnaryOperators above
        // The value of `multiplier` above is preserved in the lambda function for every iteration
        for (IntUnaryOperator fn : fns) {
            System.out.println(fn.applyAsInt(10));
        }
    }

    /* Foreach loop can be expanded to the explicit version below */
    public static void explicitForeach() {
        // Iterator of primes; same as above
        Integer[] multipliers = {2, 3, 5, 7, 11, 13, 17, 19};
        Iterable<Integer> multipliersIter = Arrays.asList(multipliers);

        Iterator<Integer> ible = multipliersIter.iterator();    // Get explicit iterator with iterator() method
        for (/* None */; ible.hasNext(); /* None */) {          // Keep calling hasNext() until all values are exhausted
            int prime = ible.next();
            System.out.println(prime);
        }
    }

    public static void main(String[] argv) {
        foreachArray();
        foreachLambda();
        explicitForeach();
    }
}
