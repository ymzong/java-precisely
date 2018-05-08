package section21;

import java.io.PrintWriter;

/**
 * A generic printable list that extends MyLinkedList<T> and adds a print() method to make it printable.
 * Type argument constraint (`T extends PrintableObj`) makes sure that each element is printable.
 */
public class MyPrintableList<T extends PrintableObj> extends MyLinkedList<T> implements PrintableObj, MyList<T> {

    /* This method prints all contents of the linked list to the PrintWriter */
    public void print(PrintWriter fs) {
        for (T elem : this) {   // this syntax is possible since MyLinkedList implements MyList<T> (hence Iterable<T>)
            fs.print(elem);
        }
    }

}

/**
 * This interface represents an object that has print method that prints itself to the PrintWriter as provided.
 */
interface PrintableObj {

    /**
     * Prints itself to the provided PrintWriter.
     * @param fs target PrintWriter
     */
    void print(PrintWriter fs);
}