package section22;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class showcases some practical uses of Sets, especially HashSet (vs. Arrray), LinkedHashSet, and TreeSet.
 */
public class SetUsage {

    static final String reservedWords[] = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else", "extends", "final", "finally",
            "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while" };

    /**
     * In this example, we test whether a given string is part of Java's reserved words.
     * As benchmark shows, HashSet.contains() is much faster (3x) than Arrays.binarySearch() or TreeSet.contains().
     */
    public static void testMembership() {
        Set<String> wordSet = new HashSet<>(Arrays.asList(reservedWords));
        final int ITERATION_COUNT = 2000000;
        final String word1 = "goto";
        final String word2 = "hashset";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            Arrays.binarySearch(reservedWords, word1);
            Arrays.binarySearch(reservedWords, word2);
        }
        long endTime = System.currentTimeMillis();
        System.out.format("Time elapsed using Arrays.binarySearch: %dms%n", endTime - startTime);   // 78ms

        startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            wordSet.contains(word1);
            wordSet.contains(word2);
        }
        endTime = System.currentTimeMillis();
        System.out.format("Time elapsed using HashSet.contains: %dms%n", endTime - startTime);      // 22ms
    }

    /**
     * In this example, we show the use of LinkedHashSet for de-duping a list of values while maintaining their order.
     */
    public static void dedupInOrder() {
        String[] values = new String[] { "a.java", "b.java", "d.java", "b.java", "c.java", "e.java", "b.java" };
        LinkedHashSet<String> result = new LinkedHashSet<>();

        for (String val : values) {
            result.add(val);
        }

        System.out.println(result);
    }

    public static void main(String[] argv) {
        testMembership();
        dedupInOrder();
    }
}
