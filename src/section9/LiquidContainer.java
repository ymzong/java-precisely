package section9;

/**
 * This abstract class represents the notion of a liquid container.
 * This is not a complete definition of a class since only the implementation of fill() method is supplied.
 * Created by jzong on 3/2/18.
 */
abstract class LiquidContainer {
    // The amount of liquid that the container is holding
    double contents;

    // Returns the capacity of the container
    abstract double capacity();

    // This method fills up the container up to its capacity
    void fill(double amount) {
        contents = Math.min(contents + amount, capacity());
    }
}
