package section17.vessel;

/**
 * Based on section9.RectangularContainer, but with different member controls.
 */
public class RectangularContainer extends Vessel {

    protected final double length, width, height;   // Want this to be accessible in subclasses wherever they are

    public RectangularContainer(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double capacity() {
        return length * width * height;
    }

    public String toString() {
        return String.format("section17.vessel.RectangularContainer(length=%f, width=%f, width=%f)",
                length, width, height);
    }

}
