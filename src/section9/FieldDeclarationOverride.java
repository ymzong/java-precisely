package section9;

/**
 * This experiment shows the behavior of field declaration override.
 * The main function creates the objects and manipulates their overridden fields.
 * (Based on Example 56 in book)
 * Created by jzong on 3/4/18.
 */
public class FieldDeclarationOverride {
    public static void main(String[] argv) {
        /* Basic Case: Instantiate BaseClass and examine its fields */
        BaseClass b = new BaseClass(100);
        System.out.format("B as BaseClass: vf=%d, sf=%d.%n",
                b.vf,           // b.vf=100 due to constructor of BaseClass
                BaseClass.sf            // b.sf=101 due to constructor of BaseClass
        );

        /* Instantiate ExtendedClass and examine its fields as is and as BaseClass */
        ExtendedClass e = new ExtendedClass(200);
        BaseClass eAsB = e;
        System.out.format("E as ExtendedClass: vf=%d, sf=%d.%n",
                e.vf,           // e.vf=200 despite super(n+20), since the following line vf=n defines its *own* copy
                ExtendedClass.sf            // e.sf=202 due to sf=n+2; super(n+20) sets the static field of BaseClass
        );
        System.out.format("E as BaseClass: vf=%d, sf=%d.%n",
                eAsB.vf,        // eAsB.vf=220 due to super(n+20), which instantiates the field of BasicClass
                BaseClass.sf         // eAsB.sf=221 due to super(n+20), which sets BaseClass.sf as 121
        );

        /* Instantiate FurtherExtendedClass and examine its fields as is ans as superclasses */
        FurtherExtendedClass f = new FurtherExtendedClass(300);
        ExtendedClass fAsE = f;
        BaseClass fAsB = f;
        System.out.format("F as FurtherExtendedClass: vf=%d, sf=%d.%n",
                f.vf,           // f.vf=300 despite super(n+40); vf=n sets the field in FurtherExtendedClass
                ExtendedClass.sf            // f.sf=304 despite super(n+40); sf=n+4 sets the field in ExtendedClass(!)
        );
        System.out.format("F as ExtendedClass: vf=%d, sf=%d.%n",
                fAsE.vf,        // fAsE.vf=340 due to super(n+40)
                ExtendedClass.sf         // fAsE.sf=304, since super(n+40) and sf=n+4 both modify the field of ExtendedClass(!)
        );
        System.out.format("F as BaseClass: vf=%d, sf=%d.%n",
                fAsB.vf,        // fAsB.vf=360 due to super(n+40) invoking super(n+20)
                BaseClass.sf         // fAsB.sf=361 due to super(n+40) invoking super(n+20), which sets BaseClass.sf as 361
        );
    }
}

/* Follwing are three classes, where each class extends the previous one */
class BaseClass {
    int vf;                     // Defines non-static field
    static int sf;              // Defines static field

    BaseClass(int n) {
        vf = n;
        sf = n + 1;
    }
}

class ExtendedClass extends BaseClass {
    int vf;                     // ExtendedClass now has two vf fields -- one from BaseClass, and one from itself
    static int sf;              // ExtendedClass has only one sf field -- this one overrides superclass

    ExtendedClass(int n) {
        super(n + 20);
        vf = n;
        sf = n + 2;
    }
}

class FurtherExtendedClass extends ExtendedClass {
    int vf;                     // FurtherExtendedClass has three vf fields
                                // FurtherExtendedClass still has a single sf field

    FurtherExtendedClass(int n) {
        super(n + 40);
        vf = n;
        sf = n + 4;
    }
}
