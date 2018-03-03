package section9;

/**
 * RectangularContainer implements a generic rectangular LiquidContainer
 * Created by jzong on 3/2/18.
 */
public class RectangularContainer extends LiquidContainer {
    /* Adds additional non-static fields */
    double length, width, height;

    /* Implements constructor for RectangularContainer, which LiquidContainer is missing */
    public RectangularContainer(double l, double w, double h) {
        this.length = l;
        this.width = w;
        this.height = h;
    }

    /* Implements method capacity(), whose signature was defined in LiquidContainer */
    double capacity() {
        return length * width * height;
    }

    /* Implements toString() method to give it descriptive toString result */
    public String toString() {
        return String.format("RectangularContainer(length=%f, width=%f, height=%f, capacity=%f, contents=%f)%n",
                this.length, this.width, this.height, this.capacity(), this.contents);
    }

}
