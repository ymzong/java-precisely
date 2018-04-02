package section17.vessel;

/**
 * Mostly the same hierarchy as section9.LiquidContainer, but with intentional package separation.
 */
public abstract class Vessel {

    private double contents;
    public abstract double capacity();

    public final void fill(double amount) {
        contents = Math.min(capacity(), contents + amount);
    }

    // Only defines a getter for contents
    public final double getContents() {
        return contents;
    }

}
