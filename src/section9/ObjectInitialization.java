package section9;

/**
 * This class demonstrates the sequence of object instantiation & initialization.
 * Created by jzong on 3/9/18.
 */
public class ObjectInitialization {

    public static void main(String[] argv) {
        /**
         * Explanation for what happens when `new D2()` is called:
         * - first, value is initialized to 0 by D2;
         * - the constructor of the direct superclass, D1(), is called *before* executing the body of D2();
         * - skip this step since no non-static initializer blocks are found;
         * - run the body of D2();
         *
         * As a result, D1(), and therefore m2() are called when value=0.
         * Then, m2() calls m1(), and the latter is overridden in D2. As a result, value 0 is printed.
         */
        D2 d2 = new D2();
    }

}

class D1 {
    D1() {
        m2();
    }

    void m1() {
        System.out.println("D1.m1");
    }

    void m2() {
        System.out.println("D1.m2"); m1();
    }
}

class D2 extends D1 {
    int value;

    D2() {
        value = 42;
    }

    void m1() {
        System.out.format("D2.m1 while value is %d.%n", value);
    }
}
