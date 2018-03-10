package section9;

/**
 * This class demonstrates the use of local classes with explanations. Not runnable.
 * Created by jzong on 3/9/18.
 */
public class LocalClasses {
    // Standard fields
    static int staticField;
    int nonStaticField;

    // Static member class
    static class StaticMemberClass {
        // *Cannot* access LocalClasses.nonStaticField since SMC is a static member
        // *Can* have static and non-static members
        static int innerStaticField = staticField + LocalClasses.staticField;   // Use enclosing class name for access
        int innerNonStaticField = staticField + LocalClasses.staticField;
    }

    // Non-Static member class (inner class)
    class NonStaticMemberClass {
        // *Can* access static and non-static members
        // *Cannot* define static inner members (unless final)
        // Following two definitions are equivalent:
        int innerNonStaticField =   staticField                 + nonStaticField;
        int anotherInnerField =     LocalClasses.staticField    + LocalClasses.this.nonStaticField;

        static final int innerStaticField = 29;
    }

    void method() {
        class LocalClass {
            int m(int arg) {
                return staticField + nonStaticField + arg;
            }
        }
    }
}
