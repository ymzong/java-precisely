package section21;

/**
 * This examples shows several examples of wildcard type parameters for generic class.
 */
public class WildcardTypeArgument {

    public static void main(String[] argv) {
        /**
         * Preferred use cases:
         * <? super T> when you want to (s)upply it a T;
         * <? extends T> when you want to (e)xtract from it a T.
         */
        GenericContainer<? super Integer> intContainer = new GenericContainer<Number>(Double.valueOf(3.0));
        intContainer.setVal(Integer.valueOf(42));       // supply T to <? super T> type
        System.out.printf("value type: %s, value: %d%n", intContainer.getVal().getClass(), intContainer.getVal());

        GenericContainer<? extends Number> numContainer = new GenericContainer<Integer>(3);
        Number n = numContainer.getVal();               // get T from <? extends T> type
        System.out.printf("value type: %s, value: %d%n", numContainer.getVal().getClass(), numContainer.getVal());

        /* Tricky case: <?> pretty much says nothing... */
        GenericContainer<?> mysteryContainer = new GenericContainer<Integer>(3);
        Object o = mysteryContainer.getVal();           // No more type info other than Object
        o = (Object) Integer.valueOf(42);
        // The following doesn't work since Java doesn't know the actual container type, and it prevents type corruption
        // mysteryContainer.setVal(o);
    }

}

/* Simplistic generic container with getter and setter */
class GenericContainer<T> {

    private T val;

    public GenericContainer(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}