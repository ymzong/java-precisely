package section8;

import java.util.Arrays;
import java.util.List;

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
        Integer[] testArray = { 5, 2, 18, 7, -9, 27, 3, 12, 16, 8 };

        // Arrays.asList -- converts an Array into List
        List<Integer> convertedList = Arrays.asList(testArray);
        for (int elem : convertedList) {
            System.out.format("%d ", elem);
        }
    }

    public static void main(String[] argv) {
        arrayInitialization();
        multiDimensionalArrays();
        arrayUtils();
    }
}
