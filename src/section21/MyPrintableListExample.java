package section21;

import java.io.PrintWriter;

/**
 * This example uses MyPrintableList declared earlier.
 */
public class MyPrintableListExample {

    public static void main(String[] argv) {
        MyPrintableList<PrintableInteger> myList = new MyPrintableList<>();
        myList.append(new PrintableInteger(2));
        myList.append(new PrintableInteger(3));
        myList.append(new PrintableInteger(5));
        myList.append(new PrintableInteger(7));

        PrintWriter stdout = new PrintWriter(System.out);
        PrintWriter stderr = new PrintWriter(System.err);
        myList.print(stdout);
        myList.print(stderr);
        stdout.flush();
        stderr.flush();
    }
}

/**
 * PrintableInteger simply holds an immutable integer, and defines a toString() method to be used by print(PrintWriter).
 */
class PrintableInteger implements PrintableObj {

    public final int val;       // intentionally immutable

    public PrintableInteger(int val) {
        this.val = val;
    }

    public void print(PrintWriter fs) {
        fs.write(val);          // what's written to `fs` is determined by its toString method
    }

    public String toString() {
        return String.format("PrintableInteger(val=%d)", val);
    }
}
