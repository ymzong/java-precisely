package section22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * This example calculates the intersection closure of a set of sets. It demonstrates how Collections can exist within
 * Collections and how hashing / equals() still makes sense.
 */
public class IntersectionClosure {

    /**
     * This method calculates the intersection closure of the input set, which is the minimal superset of input set,
     * where for every pair of sets s_i and s_j from input, their intersection is also in the closure.
     *
     * Algorithm: while "workqueue" is non-empty, pick an element e and move it from input set to result set. Then, for
     * intersections of e with any existing set within result set, if the intersection does not exist in result set,
     * add it to the workqueue.
     */
    public static <T> Set<Set<T>> intersectionClosure(Set<Set<T>> input) {
        Set<Set<T>> result = new HashSet<>();
        LinkedList<Set<T>> workQueue = new LinkedList<>(input);  // create LinkedList from Set

        while (!workQueue.isEmpty()) {
            Set<T> currentEntry = workQueue.removeFirst();
            result.add(currentEntry);

            for (Set<T> resultEntry : result) {
                Set<T> tmp = new HashSet<>(currentEntry);
                tmp.retainAll(resultEntry);
                if (!result.contains(tmp)) {
                    workQueue.add(tmp);
                }
            }
        }

        return result;
    }

    public static void main(String argv[]) {
        // Consider the following input: { {1}, {1,3}, {2,3,4}, {1,4}, {4} }
        Set<Set<Integer>> original = new HashSet<>();
        original.add(makeSet(1));
        original.add(makeSet(1,3));
        original.add(makeSet(2,3,4));
        original.add(makeSet(1,4));
        original.add(makeSet(4));

        System.out.println(intersectionClosure(original));
    }

    // Helper that generates HashSet given vararg of integers
    private static Set<Integer> makeSet(int... values) {
        Set<Integer> result = new HashSet<>();
        for (int val : values) {
            result.add(val);
        }
        return result;
    }
}