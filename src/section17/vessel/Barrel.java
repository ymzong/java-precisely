package section17.vessel;

/**
 * Based on section9.BarrelContainer, but with tighter member controls.
 */
public class Barrel extends Vessel {

    private final double radius, height;        // 'private final' such that it's not directly accessible from outside

    public Barrel(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double capacity() {
        return Math.PI * radius * radius * height;
    }

    public String toString() {
        return String.format("section17.vessel.Barrel(radius=%f, height=%f)", radius, height);
    }
}
