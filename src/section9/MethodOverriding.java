package section9;

/**
 * This class demonstrates the effect of method overloading, overriding, and hiding across classes.
 * Created by jzong on 3/8/18.
 */
public class MethodOverriding {
    public static void main(String[] argv) {
        int i = 42;
        Integer ii = new Integer(i);
        double d = 3.14;

        /* Instantiate C1 and C2, and then assign C2 to C1 type variable. */
        C1 nativeC1 = new C1();
        C2 c2 = new C2();
        C1 c2AsC1 = c2;

        /**
         * Overall principle:
         * If signature matching based on variable type (*not* underlying object type) returns static method, then
         * simply use VariableType.staticMethod().
         * Otherwise, check for a fitting method starting from the *underlying* object type (in this case C2 then C1).
         *
         * In order to check for matching signature, follow the three tiers:
         * - no [un-]boxing or param lists allowed
         * - only [un-]boxing allowed
         * - both allowed
         */
        // Trivial section with only C1
        nativeC1.m1(d);     // Calls static C1.m1 due to matching signature
        nativeC1.m1(i);     // Calls non-static C1.m1 due to more specific signature
        nativeC1.m1(ii);    // Calls non-static C1.m1 after unboxing
        nativeC1.m2(i);     // Trivial due to exact signature match

        /**
         * Here, Target Type is always C1. We find a matching signature in C1 first, and depending on whether it's
         * a static method, we pick the static method from C1, or pick the non-static method by starting looking
         * at C2, and then C1 (if not found in C2).
         *
         * Signature lookup follows the three tiers above.
         */

        System.out.println();
        c2AsC1.m1(d);       // Target Type: C1; Target Sig: static m1(d);           Result: C1.m1(d)
        c2AsC1.m1(i);       // Target Type: C1; Target Sig: m1(int);                Result: C2.m1(int)
        c2AsC1.m1(ii);      // Target Type: C1; Target Sig: m1(int) after unboxing; Result: C2.m1(int)
        c2AsC1.m2(i);       // Target Type: C1; Target Sig: m2(int);       Result: C1.m2(int) since no unboxing at first

        /**
         * Here, Target Type is always C2. We find the matching signature in C2 (and C1 if doesn't exist).
         * Signature lookup follows the three tiers above.
         */
        System.out.println();
        c2.m1(i);           // Target Type: C2; Target Sig: m1(int);                Result: C2.m1(int)
        c2.m1(d);           // Target Type: C2; Target Sig: static m1(double);      Result: C2.m1(double)
        c2.m2(i);           // Target Type: C2; Target Sig: m2(int) w/o unboxing;   Result: C1.m2(int)
        c2.m2(ii);          // Target Type: C2; Target Sig: m2(Integer) match;      Result: C2.m2(Integer)
        c2.m3(ii);          // Target Type: C2; Target Sig: m3(int) w/ unboxing;    Result: C2.m3(int)
        c2.m4(i);           // Target Type: C2; Target Sig: m4(Integer) w/ boxing;  Result: C2.m4(Integer)
    }
}

class C1 {
    static void m1(double d) {
        System.out.println("Static C1.m1(double)");
    }

    void m1(int i) {
        System.out.println("Non-Static C1.m1(int)");
    }

    void m2(int i) {
        System.out.println("Non-Static C1.m2(int)");
    }
}

class C2 extends C1 {
    static void m1(double d) {
        System.out.println("Static C2.m1(double)");
    }

    void m1(int i) {
        System.out.println("Non-Static C2.m1(int)");
    }

    void m2(double d) {
        System.out.println("Non-Static C2.m2(double)");
    }

    void m2(Integer ii) {
        System.out.println("Non-Static C2.m2(Integer)");
    }

    void m3(int i) {
        System.out.println("Non-Static C2.m3(int)");
    }

    void m4(Integer ii) {
        System.out.println("Non-Static C2.m4(Integer)");
    }
}