package section22;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This example demonstrates key methods from Comparator<T> functional interface.
 */
public class ComparatorMethods {

    public static void main(String[] argv) {
        String s1 = "OAuth2";
        String s2 = "SAML";
        String s3 = "Kerberos";

        /* Comparator.compare: abstract function that specifies ordering between values */
        // First, we use the long form to define a comparator in BackwardStringComparator
        testComparator(new BackwardStringComparator());

        /* Comparator.comparing: transforms compared elements with Function, and compare results by natural order */
        // Here, we use a short form to define a Comparator equivalent to the above
        Function<String, String> revertString = s -> new StringBuilder(s).reverse().toString();
        Comparator<String> revertStringCmp = Comparator.comparing(revertString);
        testComparator(revertStringCmp);

        /* Comparator.comparing also supports a comparator for the transformed values */
        // Here, we define a Comparator that reverses the order from BackwardStringComparator (see cleaner impl below)
        Comparator<String> revertStringCmpDesc = Comparator.comparing(revertString, (v1, v2) -> v2.compareTo(v1));
        testComparator(revertStringCmpDesc);

        /* Comparator.naturalOrder, Comparator.reverseOrder: obtains the native comparator of a type (or its reverse) */
        // Here, we create Comparators for native String ordering as well as its reverse.
        Comparator<String> nativeCmp = Comparator.naturalOrder();
        testComparator(nativeCmp);
        Comparator<String> reverseCmp = Comparator.reverseOrder();
        testComparator(reverseCmp);

        /* Comparator.nullsFirst, Comparator.nullsLast: returns a null-safe Comparator by putting nulls first/last */
        // Here, we generate null-safe versions of revertStringCmp above
        testComparator(Comparator.nullsFirst(revertStringCmp), true);
        testComparator(Comparator.nullsLast(revertStringCmp), true);

        /* Comparator.reversed: return the reversed version of an existing Comparator */
        // Here, we generate revertStringCmpDesc (L31) with cleaner syntax
        revertStringCmpDesc = Comparator.comparing(revertString).reversed();
        testComparator(revertStringCmpDesc);

        /* Comparator.thenComparing: chains Comparators together; supports another comparator, a transformation
         * function, or both */
        // Here, we create a Comparator that first compares the second char, and then the entire string
        // Note: need to specify type argument below for type inference (https://stackoverflow.com/q/50442956/2448960)
        Comparator<String> secondCharThenStringCmp = Comparator
                .<String>comparingInt(s -> s.charAt(1))
                .thenComparing(Comparator.naturalOrder());
        testComparator(secondCharThenStringCmp);
    }

    static void testComparator(Comparator<String> cmp) {
        testComparator(cmp, false);
    }

    static void testComparator(Comparator<String> cmp, boolean withNull) {
        String s1 = "OAuth2";
        String s2 = "SAML";
        String s3 = "Kerberos";

        List<String> auths = new ArrayList<String>();
        auths.add(s1);
        auths.add(s2);
        auths.add(s3);
        if (withNull) {
            auths.add(null);
        }

        auths.sort(cmp);
        System.out.println(auths);
    }
}

/**
 * This comparator for Strings uses native String ordering, except that the strings are looked at *backwards*.
 */
class BackwardStringComparator implements Comparator<String> {

    private static String revertString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    @Override
    public int compare(String s1, String s2) {
        return revertString(s1).compareTo(revertString(s2));
    }
}