package section8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by jzong on 2/24/18.
 */
public class ArraysDemonstration {

    static void arrayInitialization() {
        // Array type notation: elem[];
        // Array creation:      new elem[length];
        int[] intArray = new int[12];
        System.out.format("Elements in new array with primitive elems are set to %d%n", intArray[0]);
        Object[] objArray = new Object[24];
        System.out.format("Elements in new array with reference type elems are set to %s%n", objArray[0]);

        // Array length: arr.length
        System.out.format("Length of intArray is %d%n", intArray.length);

        // Element assignments are "type-checked" during run-time
        Number[] numArray = new Integer[10];        // Okay because Integer is subclass of Number
        Double d = new Double(3.14);
        Integer i = new Integer(42);
        Number n = i;

        numArray[0] = i;                            // Okay because Integer is subtype of Number
        numArray[1] = n;                            // Okay because Integer is subtype of Number
        try {
            numArray[2] = d;                        // Not okay because Double is not subtype of Number
        } catch (ArrayStoreException e) {
            System.out.println("Array element assignments are type-checked during runtime");
        }

        // Array initializer syntax: { ... }
        // New array is created upon every evaluation of array initializer
        String[] names = { "Jimmy", "Peter", "Thomas" };
        String[] moreNames = new String[] { "Jimmy", "Peter", "Thomas" };
        System.out.format("Length of names = %d; Length of moreNames = %d.%n", names.length, moreNames.length);
        System.out.format("Two constant arrays are the same? %b%n", names == moreNames);

        System.out.println(replaceCharWithChar("Hello World", 'o', '0'));
    }

    /* Given a string, replace all occurrences of one char with another */
    static String replaceCharWithChar(String s, char c1, char c2) {
        char[] result = new char[s.length()];       // Improves performance with single allocation
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c1) {
                result[i] = c2;
            } else {
                result[i] = s.charAt(i);
            }
        }
        return new String(result);                  // Create new string object from char array
    }

    static void multiDimensionalArrays() {
        // Multidimensional array type notation: elem[][]
        // Array creation (rectangular shape):   new elem[M][N]
        // Array creation (outer-layer only):    new elem[M][]
        int[][] rectangularArray = new int[3][3];
        int[][] flexibleArray = new int[3][];

        // Array initializer syntax: { { ... }, { ... }, ... }, or new elem[][] { ... }
        int[][] zeroRectangle = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
        int[][] zeroTriangle = { { 0 }, { 0, 0 }, { 0, 0, 0 } };
        System.out.format("Length of three rows: %d, %d, %d -- multi-dimensional arrays can be jagged.%n",
                zeroTriangle[0].length, zeroTriangle[1].length, zeroTriangle[2].length);

        countCodonFrequency("AACGTACGTACGTAGCTGCTGCTAGTCGATAGCTA");
    }

    /* Given a DNA sequence string, return the frequency count of its codons */
    static void countCodonFrequency(String s) {
        // Create reference table from ASCII to ACGT (indexed as 0, 1, 2, and 3)
        int[] asciiMapping = new int[128];
        for (int i = 0; i < 128; i++) {
            asciiMapping[i] = -1;
        }
        asciiMapping['a'] = asciiMapping['A'] = 0;
        asciiMapping['c'] = asciiMapping['C'] = 1;
        asciiMapping['g'] = asciiMapping['G'] = 2;
        asciiMapping['t'] = asciiMapping['T'] = 3;

        // Accumulate frequency array for each codon -- freq[c1][c2][c2]
        int[][][] freq = new int[4][4][4];
        for (int i = 0; i + 2 < s.length(); i += 3) {
            int code1 = asciiMapping[s.charAt(i)];
            int code2 = asciiMapping[s.charAt(i+1)];
            int code3 = asciiMapping[s.charAt(i+2)];

            if (code1 >= 0 && code2 >= 0 && code3 >= 0) {
                freq[code1][code2][code3]++;
            }
        }

        // Print out result in readable format by mapping codes back to ACGT chars
        char[] indexToChar = { 'A', 'C', 'G', 'T' };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (freq[i][j][k] > 0) {
                        System.out.format("Frequency of %c%c%c: %d%n",
                                indexToChar[i], indexToChar[j], indexToChar[k], freq[i][j][k]);
                    }
                }
            }
        }
    }

    /* Demonstration of Array util functions */
    static void arrayUtils() {
        Integer[] testArray = { 5, 2, 18, 1, -9, 27, 3, 12, 16, 8 };

        // Arrays.asList -- converts an Array into List
        List<Integer> convertedList = Arrays.asList(testArray);
        for (int elem : convertedList) {
            System.out.format("%d ", elem);
        }
        System.out.println();

        // Arrays.toString -- represents an Array as String
        // Arrays.binarySearch -- performs binary search on sorted array, supports custom Comparator
        int[] sortedPrimes = { 2, 3, 5, 7, 11, 13 };
        String primesList = Arrays.toString(sortedPrimes);
        System.out.format("List of primes: %s%n", primesList);
        System.out.format("Result from searching for 7: %1$d -> found at index %1$d.%n",
                Arrays.binarySearch(sortedPrimes, 7));
        int idx = Arrays.binarySearch(sortedPrimes, 6);
        System.out.format("Result from searching for 6: %d -> not found in array, but *would* be %dth if it existed.%n",
                idx, -idx);

        // Arrays.equal -- performs element-by-element equality check
        int[] sortedPrimesNew = { 2, 3, 5, 7, 11, 13 };
        System.out.format("sortedPrimes and sortedPrimesNew are equal (==): %b%n", sortedPrimes == sortedPrimesNew);
        System.out.format("Same two arrays are equal by elements: %b%n", Arrays.equals(sortedPrimes, sortedPrimesNew));

        // Arrays.fill -- fills an array (optional start..end range) with a designated value
        String[] stringArray = new String[5];
        Arrays.fill(stringArray, "Java");                   // Fills the entire array (0..5)
        Arrays.fill(stringArray, 1, 4, "Awesome");          // Fills indexes 1..4
        for (int i = 0; i < stringArray.length; i++) {
            System.out.format("%s ", stringArray[i]);
        }
        System.out.println();

        // Arrays.parallelPrefix -- performs prefix-"sum" on an array, potentially in parallel
        // Requires that the binary operator is associative
        int[] testValues = { 1, 6, 2, 29 };
        Arrays.parallelPrefix(testValues, (x,y) -> x+y);
        System.out.format("Result of prefix sum : %s%n", Arrays.toString(testValues));

        // Arrays.parallelSetAll -- sets values in an array based on a function that takes in the index
        int[] multiplesOfThree = new int[8];
        Arrays.parallelSetAll(multiplesOfThree, i -> i*3);
        System.out.format("Multiples of three: %s%n", Arrays.toString(multiplesOfThree));

        // Arrays.parallelSort -- sorts an array using parallel sort
        // Arrays.sort         -- sorts an array using quicksort (unstable)
        // Both can optionally take in sort range and Comparator
        int[] values = { 12, 4, -9, 1, 6 };
        Arrays.parallelSort(values, 0, 2);              // Only sort the first two values
        System.out.format("Partially sorted array: %s%n", Arrays.toString(values));
        Arrays.sort(values);                            // Sort the whole array
        System.out.format("Fully sorted array: %s%n", Arrays.toString(values));

        // Arrays.stream -- returns a sequential Stream based on values in the array (with optional index range)
        IntStream valuesStream = Arrays.stream(values);
        int[] evenValues = valuesStream.filter(v -> v % 2 == 0).toArray();
        System.out.format("Even values in valuesStream: %s%n", Arrays.toString(evenValues));
    }

    public static void main(String[] argv) {
        arrayInitialization();
        multiDimensionalArrays();
        arrayUtils();
    }
}
