package section13;

/**
 * This class demonstrates the use of default and static methods in interface definition.
 */
public class DefaultStaticMethods {

    public static void main(String[] argv) {
        TestImpl c = new TestImpl();
        c.barkInstance();           // "Regular" method in interface -- instance method
        c.barkDefault();            // `default` method in interface -- instance method with default definition
        TestInterface.barkStatic(); // `static`  method in interface -- static method on Interface **only**

        System.out.println();

        TestImplOverride co = new TestImplOverride("Max");
        co.barkInstance();          // "Regular" method in interface; ditto
        co.barkDefault();           // Overridden `default` method in interface -- instance method
    }
}

// Sample interface with "regular", static, and default methods.
interface TestInterface {

    /* Instance method description */
    void barkInstance();

    /* Interface static method implementation */
    static void barkStatic() {
        System.out.println("Barking from static method of TestInterface!");
    }

    /* Default instance method implementation */
    default void barkDefault() {
        System.out.println("Barking from default method of TestInterface!");
    }

}

/* Simple implementation of TestInterface; leaves default method untouched */
class TestImpl implements TestInterface {

    /* Implements instance method from interface */
    public void barkInstance() {
        System.out.println("Barking from instance method of TestImpl (implementing TestInterface)!");
    }

}

/* More interesting implementation of TestInterface; overrides default method */
class TestImplOverride implements TestInterface {

    String name;

    public TestImplOverride(String name) {
        this.name = name;
    }

    /* Implements instance method from interface */
    public void barkInstance() {
        System.out.println("Barking from instance method of TestImplOverride (implementing TestInterface)!");
    }

    /* Overrides default instance method from interface */
    public void barkDefault() {
        System.out.println("Overriding default barking method! Woooof!");
        System.out.println("My name is " + name + "!");
    }

}