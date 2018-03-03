package section9;

/**
 * BarrelContainer implements a barrel-shaped container and extends LiquidContainer abstract class.
 * Created by jzong on 3/3/18.
 */
public class BarrelContainer extends LiquidContainer {
    /* Additional non-static fields */
    double radius, height;

    /* Implements Barrel-specific constructor */
    public BarrelContainer(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    /* Implements capacity() method according to volume formula */
    double capacity() {
        return Math.PI * radius * radius * height;
    }

    /* Implements toString() override */
    public String toString() {
        return String.format("Barrel(radius=%f, height=%f, capacity=%f, contents=%f",
                this.radius, this.height, this.capacity(), this.contents);
    }
}
