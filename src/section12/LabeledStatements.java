package section12;

/**
 * This class demonstrates the use of labeled statements (usually not recommended).
 */
public class LabeledStatements {

    public static void main(String[] argv) {
        outer: {
            String line = "";
            while (true) {
                line += "*";
                System.out.println(line);

                if (line.length() > 5) {
                    break outer;    // In this case, same as using `break` itself
                }
            }
        }
        // Execution continues here after `break outer`

        // Determine whether s2 is substring of s1
        String s1 = "123javascript";
        String s2 = "java";
        outer:
            for (int start = 0; start + s2.length() < s1.length(); start++) {   // start index for substring
                for (int idx = 0; idx < s2.length(); idx++) {
                    if (s1.charAt(start + idx) != s2.charAt(idx)) {
                        continue outer;     // directly skip to next iteration of the outer loop (as labeled)
                    }
                }
                System.out.format("match found with start=%d.%n", start);
                System.exit(0);
            }
        System.out.println("match not found!");
    }

}
