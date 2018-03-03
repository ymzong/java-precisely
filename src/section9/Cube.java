package section9;

/**
 * The implementation of Cube extends class RectangularContainer
 * Created by jzong on 3/2/18.
 */
public class Cube extends RectangularContainer {

    /* This constructor invokes the constructor of its immediate parent (RectangularContainer) */
    public Cube(double side) {
        super(side, side, side);
    }

    /* Overrides toString method of parent class */
    public String toString() {
        return String.format("Cube(side=%f, capacity=%f, contents=%f)",
                this.length, this.capacity(), this.contents);       // Uses this.length in superclass
    }
}
