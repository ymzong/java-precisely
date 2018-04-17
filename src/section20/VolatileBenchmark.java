package section20;

/**
 * This class shows that volatile field modifier can prevent JIT compiler from performing optimizations.
 *
 * In the example below (arrays with roughly 30 million elements), using volatile causes runtime to ~4x.
 */
public class VolatileBenchmark {

    public static void main(String[] argv) {
        RegularIntArray ria = new RegularIntArray();
        VolatileIntArray via = new VolatileIntArray();

        // Benchmark RegularIntArray
        long startTime = System.currentTimeMillis();
        ria.isSorted();
        long endTime = System.currentTimeMillis();
        System.out.format("isSorted() on non-volatile array with %d elements: %dms.%n",     // 34ms
                RegularIntArray.LENGTH, endTime - startTime);

        // Benchmark VolatileIntArray
        startTime = System.currentTimeMillis();
        via.isSorted();
        endTime = System.currentTimeMillis();
        System.out.format("isSorted() on volatile array with %d elements: %dms.%n",         // 128ms
                VolatileIntArray.LENGTH, endTime - startTime);
    }
}

/**
 * This class describes a simple wrapper for an int array, with a method to check whether the array is sorted.
 *
 * Note that the array field is non-volatile in this case, thus enabling JIT to perform optimizations.
 */
class RegularIntArray {
    final static int LENGTH = 1024 * 1024 * 30;
    private int[] array;

    public RegularIntArray() {
        array = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            array[i] = i;
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {    // intentionally use array.length
            if (array[i] > array[i+1]) {
                return false;
            }
        }

        return true;
    }
}

/**
 * This class is identical to the above, except that the array field *is* volatile.
 *
 * This prevents optimizations performed by JIT, thereby causing extra work.
 */
class VolatileIntArray {
    final static int LENGTH = 1024 * 1024 * 30;
    volatile private int[] array;

    public VolatileIntArray() {
        array = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            array[i] = i;
        }
    }

    /**
     * In this method, volatile modifier on array field causes a lot of extra work.
     * For example, array.length must be freshly calculated for every iteration (not for non-volatile array above).
     *
     * @return Whether array is sorted.
     */
    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {    // intentionally use array.length (array is volatile)
            if (array[i] > array[i+1]) {
                return false;
            }
        }

        return true;
    }
}