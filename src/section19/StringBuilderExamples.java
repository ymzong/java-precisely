package section19;

import java.util.Random;

/**
 * This class demonstrates the use and efficiency of StringBuilder.
 *
 * Conclusion: StringBuilder is pretty much backed by an amortized char[] at its core.
 */
public class StringBuilderExamples {

    /* This method benchmarks the performance of StringBuilder append vs. String concat */
    public static void appendBenchmark() {
        final int iterations = 50000;

        // Option A: Concatenate string in for loop [6409ms]
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.format("Naive String concats over %d iterations: %dms.%n", iterations, endTime - startTime);

        // Option B: Use StringBuilder.append and convert to String at the end [3ms]
        startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append(i);
        }
        str = sb.toString();
        endTime = System.currentTimeMillis();
        System.out.format("StringBuffer appends over %d iterations: %dms.%n", iterations, endTime - startTime);
    }

    /* This method benchmarks the performance of StringBuilder replace vs. String rebuild with scanning */
    public static void replaceBenchmark() {
        char replaceFrom = 'A';
        String replaceTo = "Teemo";

        final int length = 500000;
        StringBuffer rs1 = new StringBuffer(length);    // can initialize StringBuffer with pre-defined length
        StringBuffer rs2 = new StringBuffer(length);

        // Populate two StringBuffers above with random chars
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            rs1.append((char) r.nextInt(256));
            rs2.append((char) r.nextInt(256));
        }

        // Option A: Replace a char with string by calling StringBuilder.replace [46ms]
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (i  < rs1.length()) {
            if (rs1.charAt(i) == replaceFrom) {
                rs1.replace(i, i + 1, replaceTo);
                i += 5;     //replaceTo.length();
            } else {
                i++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.format("Replace StringBuffer with %d chars using built-in method: %dms.%n",
                length, endTime-startTime);

        // Option B: Rebuild a new StringBuffer incrementally (trade-off: more memory) [13ms]
        startTime = System.currentTimeMillis();
        StringBuffer result = new StringBuffer();
        for (i = 0; i < rs2.length(); i++) {
            char currentChar = rs2.charAt(i);
            if (currentChar == replaceFrom) {
                result.append(replaceTo);
            } else {
                result.append(currentChar);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.format("Replace StringBuffer with %d chars using incremental rebuild: %dms.%n",
                length, endTime-startTime);
    }

    public static void main(String[] argv) {
        appendBenchmark();      // StringBuffer.append is very cheap
        replaceBenchmark();     // ...not so much for SB.replace -- better off building new StringBuffer incrementally
    }

}