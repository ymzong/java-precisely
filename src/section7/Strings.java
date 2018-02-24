package section7;

/**
 * Created by jzong on 2/24/18.
 */
public class Strings {

    static void basicString() {
        /* String reference assignment or .toString() returns the same object */
        String s1 = "foobar";
        String s2 = s1 + "";
        String s3 = s1;
        String s4 = s1.toString();

        System.out.println("s1 and s2 are identical objects?  " + (s1 == s2));
        System.out.println("s1 and s3 are identical objects?  " + (s1 == s3));
        System.out.println("s1 and s4 are identical objects?  " + (s1 == s4));

        /* Additions are left-associative */
        System.out.println("A" + 12 + 34);
        System.out.println(12 + 34 + "A");
    }

    static void toStringOverride() {
        demoObject demo = new demoObject(42);
        System.out.println(demo.toString());
    }

    static void stringFormatting() {
        String result = String.format("%s %d", "java", 0);
        System.out.println(result);
        System.out.format("%s %d\n", "java", 0);  // alternatively, can use .printf()

        /* Overall fmt string format: % <index>$ <flags> <width> <.precision> <conversion> */
        // Designate argument index with <index>$
        System.out.format("|%1$s|%1$s|%n", "java");
        // Default padding is right-justification; to force left-justification, use - flag
        System.out.format("|%1$8s|%1$-8s|%n", "java");
        // Formatting numbers with -+ 0,(
        System.out.format("-12345 = %1$d = %1$8d = %1$-8d = %1$08d = %1$,8d = %1$(d%n", -12345);
        System.out.format("-1.5 = %1$f = %1$10f = %1$-10f = %1$010f = %1$8.2f = %1$-8.2f%n", -1.5);
    }

    public static void main(String[] args) {
        basicString();
        toStringOverride();
        stringFormatting();
    }
}

// Dummy object to demonstrate toString() override
class demoObject {
    int value;

    public demoObject(int val) {
        this.value = val;
    }

    /* Define public toString() method to override string representation */
    public String toString() {
        return ("demoObject with value: " + value);
    }
}