package section11;

import section9.BasicPoint;

/**
 * This class showcases some caveats related to basic Java operators.
 * Created by jzong on 3/11/18.
 */
public class BasicOperators {
    public static void main(String[] argv) {
        /**
         * Test out the effect of signed & unsigned right shift:
         * Signed right shift: floor(n / 2^d)
         * Unsigned right shift: Treat n as unsigned in binary, then floor(n / 2^d)
         */
        for (int i = 0; i < 5; i++) {
            System.out.format("-15 >> %d = %3d;\t-15 >>> %1$d = %10d%n", i, -15 >> i, -15 >>> i);
        }

        /**
         * Implicit widening, narrowing, and casting
         */
        double d = 12;          // automatic widen from int to double
        byte b1 = 19;           // automatic narrowing from int to byte
        // byte b2 = 19 * 12;      illegal because compile-time constant 19*12 is out of range of byte

        b1 *= 12;               // Equivalent to b1 = (byte)(b1 * 12)
        System.out.println(b1); // hence, b1 also "overflows" but wraps around as the narrowing is done in type cast

        int i = 0;
        i += 1.5;               // Equivalent to i = (int)(i + 1.5) -> (int)(1.5) -> 1
        System.out.format("i = %d%n", i);

        /**
         * Assignment only assigns *references*, and does not copy objects
         */
        BasicPoint bp1 = new BasicPoint(12,24);
        System.out.format("bp1: %s%n", bp1);
        BasicPoint bp2 = bp1;
        System.out.format("bp2: %s%n", bp2);

        bp2.move(5, 5);
        System.out.format("bp2 after move: %s%n", bp2);
        System.out.format("bp1 after bp2 move: %s%n", bp1);

        /**
         * instanceof tells whether the type of value is subtype of given type; false if value is none
         */
        Number n1 = new Integer(17);
        Number n2 = new Double(Math.PI);
        Double n3 = null;

        System.out.format("n1 instanceof Number: %b%n", n1 instanceof Number);  // Integer is subtype of Number
        System.out.format("n1 instanceof Double: %b%n", n1 instanceof Double);  // Integer is *not* subtype of Double
        System.out.format("n3 (null) instanceof Number: %b%n", n3 instanceof Number);   // null ref always gives false
        System.out.format("n2 instanceof Double: %b%n", n2 instanceof Double);  // Exact type match
    }
}
