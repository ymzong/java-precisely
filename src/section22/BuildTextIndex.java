package section22;

import java.io.*;
import java.util.*;

/**
 * This example uses key methods exposed by SortedMap<K,V> interface as well as its TreeMap<K,V> implementation to build
 * an index on a text file, where the line number of every occurrence of every unique word is recorded.
 *
 * Note: HashMap <> SortedMap <> TreeMap is analogous to HashSet <> SortedSet <> TreeSet
 */
public class BuildTextIndex {

    // This method reads the given text file, tokenize it line-by-line, and build the index.
    // SortedMap is selected for both layers for easier printout by dictionary/numeric order.
    public static SortedMap<String, SortedSet<Integer>> indexText() {
        TreeMap<String, SortedSet<Integer>> index = new TreeMap<String, SortedSet<Integer>>();
        System.out.print("Enter filename to build index on: ");
        Scanner in = new Scanner(System.in);
        String path = in.next();
        System.out.format("Reading text from %s...%n", path);

        try {
            BufferedReader r = new BufferedReader(new FileReader(path));
            int lineNumber = 0;
            String nextLine = r.readLine();
            while (nextLine != null) {
                String[] tokens = nextLine.toLowerCase().split(" ");
                for (String token : tokens) {
                    if (!index.containsKey(token)) {
                        index.put(token, new TreeSet<>());
                    }
                    index.get(token).add(lineNumber);
                }
                lineNumber++;
                nextLine = r.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return index;
    }

    public static void main(String[] argv) {
        SortedMap<String, SortedSet<Integer>> index = indexText();
        System.out.println("Following is the resulting index");
        for (SortedMap.Entry<String, SortedSet<Integer>> entry : index.entrySet()) {
            System.out.format("`%s`: %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println("========\n\n\n\n");

        SortedMap<String, SortedSet<Integer>> subIndex = index.subMap("f", "j");    // produce a submap view
        for (SortedMap.Entry<String, SortedSet<Integer>> entry : subIndex.entrySet()) {
            System.out.format("`%s`: %s%n", entry.getKey(), entry.getValue());
        }
    }
}
