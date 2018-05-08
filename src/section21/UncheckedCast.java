package section21;

/**
 * This example shows the seemingly unexpected behavior caused by unchecked cast due to the implementation of generics
 * (i.e. type erasure).
 */
public class UncheckedCast {

    public static void main(String[] argv) {
        Container<String> foobar = new Container<>("foobar");   // initialize a String Container
        System.out.println(foobar.get());

        foobar.set(Integer.valueOf(123));   // here, we intentionally set it with a non-String value!
        System.out.println("No exceptions thrown so far...");

        Object o = foobar.get();
        foobar.get();
        System.out.println("Still no exceptions...");

        try {
            String val = foobar.get();      // This line triggers a cast of val (Integer) to String, which fails
        } catch (ClassCastException e) {
            System.out.println("Exception is thrown!");
            e.printStackTrace();
        }
    }
}

/**
 * This container simply holds a single value of type T. However, notice the parameter type of its setter (Object).
 *
 * When an input of non-T type is used to call set(), the cast is unchecked (i.e. no-op).
 * And, the cast is actually performed when the consumer excepts a value of type T being returned -- a
 * ClassCastException can be thrown if the value is not T.
 */
class Container<T> {

    private T val;

    public Container(T val) {
        this.val = val;
    }

    void set(Object val) {      // intentionally bad!
        this.val = (T) val;     // this cast is unchecked due to the implementation of Java generics
    }

    T get() {
       return val;              // the actual cast is performed here!
    }
}