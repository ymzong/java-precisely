package section22;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This example shows the use of hashCode and Comparable<T> interface.
 */
public class HashCodeAndComparable {

    public static void main(String[] argv) {
        DoublePair dp1 = new DoublePair(2.0, 5.0);
        DoublePair dp2 = new DoublePair(2.0, 5.0);
        DoublePair dp3 = new DoublePair(2.0, 5.01);
        System.out.println(dp1 == dp2);             // different objects
        System.out.println(dp1.equals(dp2));        // according to equals() override
        System.out.println(dp1.compareTo(dp3));     // according to comparesTo() override

        DoublePair dp4 = new DoublePair(2.0, 9.0);
        TreeSet<DoublePair> dpSet = new TreeSet<>();
        dpSet.add(dp1);
        dpSet.add(dp3);
        dpSet.add(dp4);
        SortedSet<DoublePair> subset = dpSet.subSet(dp2, dp4);
        System.out.println(subset);                 // only dp1 and dp3 are included in the subset
    }

}

/**
 * Simple pair with two double fields. Definitions of hashCode, equals, and compareTo are straightforward (d1 then d2).
 *
 * Comparable<T> interface describes the method `compareTo(T other)`, which is used by TreeSet and TreeMap.
 */
class DoublePair implements Comparable<DoublePair> {
    private final double d1, d2;

    public DoublePair(double d1, double d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    /**
     * This method produces a hash for the object by mashing bits of the two double fields.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(d1);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(d2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * This method overrides the default Object::equals as inherited. It compares two double fields in the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                 // easy equal case
        if (o == null || getClass() != o.getClass()) return false;  // easy unequal cases

        // Cast the other object to same class and compare field-wise
        DoublePair that = (DoublePair) o;

        if (Double.compare(that.d1, d1) != 0) return false;
        return Double.compare(that.d2, d2) == 0;
    }

    /**
     * This compareTo method compares by d1 and then d2 using the native Comparator of Double
     */
    @Override
    public int compareTo(DoublePair that) {
        return Double.compare(d1, that.d1) == 0 ? Double.compare(d2, that.d2) : Double.compare(d1, that.d1);
    }

    @Override
    public String toString() {
        return String.format("DoublePair(d1=%f, d2=%f)%n", d1, d2);
    }
}