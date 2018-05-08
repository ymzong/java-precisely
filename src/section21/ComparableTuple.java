package section21;

/**
 * This generic tuple is analogous to Comparable<T> -- given that both T and U are comparable, the tuple is also
 * comparable when we compare the first and then the second.
 *
 * Notice that bounds on type parameters (e.g. `extends Comparable<T>`) include the parameters themselves.
 */
public class ComparableTuple<T extends Comparable<T>, U extends Comparable<U>>
        implements Comparable<ComparableTuple<T,U>> {

    public final T first;
    public final U second;

    public ComparableTuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(ComparableTuple<T,U> that) {
        int firstCmp = this.first.compareTo(that.first);
        int secondCmp = this.second.compareTo(that.second);

        return (firstCmp == 0) ? secondCmp : firstCmp;          // secondCmp is tie-breaker
    }

}
