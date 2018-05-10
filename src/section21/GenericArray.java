package section21;

import java.util.ArrayList;

/**
 * Java does not allow the creation of arrays where the element type is type parameter or a constructed type.
 *
 * The reason is that writes to arrays require dynamic type-check (since T <- U makes T[] <- U[]); on the other hand,
 * T <- U does not make ArrayList<T> <- ArrayList<U>, so no dynamic type-checking is required.
 */
public class GenericArray<T> {

    public void method() {
        ArrayList<Integer>[] foo;
        /* Due to type erasure, can't tell if element in the array is subtype of ArrayList<Integer> (Integer is lost) */
        // foo = new ArrayList<Integer>[5];     <-- disallowed

        /* Due to type erasure, can't tell if element in the array is subtype of T (T itself is lost) */
        T[] bar;
        // bar = new T[10];                     <-- disallowed

        /* Due to type erasure, can't tel if element in the array is subtype of GenericArray<T> (T is lost) */
        GenericArray<T> baz;
        // baz = new GenericArray<T>[20];       <-- disallowed

        /* Workaround: Use ArrayList for this */
        ArrayList<ArrayList<Integer>> goodFoo = new ArrayList<>(5);
        ArrayList<T> gooBar = new ArrayList<>(10);
        ArrayList<GenericArray<T>> goodBaz = new ArrayList<>(20);
    }
}
