package section21;

import java.util.ArrayList;

/**
 * The examples here shows how generics allows for stronger type-checking and more expressive code.
 */
public class CaseForGenerics {

    public static void main(String[] argv) {
        /**
         * ArrayList without generics, where every entry is of Object type.
         * In the modern context, it is the Raw Type for ArrayList<T>, equivalent to ArrayList<Object>.
         */
        ArrayList names = new ArrayList();
        names.add("Peter");
        names.add("Jimmy");
        names.add(42);      // no compile-time error here
        try {
            // no compile-time error here, but it causes ClassCastException at runtime
            String name = (String) names.get(2);
        } catch (ClassCastException e) {
            System.out.println("oops");
        }

        /* ArrayList with generics, where the code is much cleaner and safer */
        ArrayList<String> coolList = new ArrayList<String>();
        coolList.add("Peter");
        coolList.add("Jimmy");
        //coolList.add(42);             // compile-time error would be triggered due to type mismatch
        String name = coolList.get(1);  // guaranteed to be safe thanks to generics; implicit cast still performed
    }

}
