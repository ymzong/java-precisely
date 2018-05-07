package section21;

import java.util.Iterator;

/**
 * This generic interface extends Iterable<T> interface by adding useful util methods.
 */
public interface MyList<T> extends Iterable<T> {

    int getLength();

    void insert(int idx, T value);

    void remove(int idx);

    T getHeadValue();

    T getTailValue();

    Iterator<T> iteratorReversed();

    /* Maps MyList of type T to type U element-wise; notice the generic method with type parameter <U> */
    <U> MyList<U> map(MapperFn<T,U> f);
}