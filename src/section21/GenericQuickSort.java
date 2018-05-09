package section21;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This example demonstrates the syntax for declaring and using generic methods.
 */
public class GenericQuickSort {

    /* Quicksorts the entire array using the given comparator */
    public static <T> void quickSort(T[] arr, Comparator<T> cmp) {
        quickSort(arr, cmp, 0, arr.length - 1);
    }

    /* Quicksorts the entire array using the default comparator from element type */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        // hacky implementation that constructs the default Comparator on the spot
        quickSort(arr, new Comparator<T>() {
            public int compare(T x, T y) {
                return x.compareTo(y);
            }
        });
    }

    /* Quicksorts the array using the given comparator between start and end index (inclusive) */
    private static <T> void quickSort(T[] arr, Comparator<T> cmp, int start, int end) {
        if (start >= end) {     // empty or singleton case
            return;
        }

        // determine pivot value, start pointer, and end pointer
        T pivot = arr[start + (end-start) / 2];
        int i = start;
        int j = end;

        while (i <= j) {
            while (cmp.compare(arr[i], pivot) < 0) { i++; }
            while (cmp.compare(pivot, arr[j]) < 0) { j--; }

            if (i <= j) {
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        quickSort(arr, cmp, start, j);
        quickSort(arr, cmp, i, end);

    }

    public static void main(String[] argv) {
        // Comparator for descending order
        Comparator<Integer> desc = new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        };

        Integer[] arr = new Integer[] { 2, 19, 54, -14, 5, 19, 9351, 1024 };
        quickSort(arr, desc);                                   // invoke generic method without type parameter
        System.out.println(Arrays.toString(arr));

        // Comparator for ascending order by the last digit
        Comparator<Integer> lastDigitAsc = new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return Math.abs(x) % 10 - Math.abs(y) % 10;
            }
        };

        GenericQuickSort.<Integer>quickSort(arr, lastDigitAsc); // invoke generic method with type parameter
        System.out.println(Arrays.toString(arr));

        GenericQuickSort.quickSort(arr);
        System.out.println(Arrays.toString(arr));               // invoke quicksort with default comparator
    }
}