package section17;

import section17.vessel.RectangularContainer;

/**
 * This class demonstrates the use of packages and the access right of accessing fields of classes in other package.
 */
class UseVessels {
    public static void main(String[] argv) {
        Cube testCube = new Cube(42);
        System.out.println(testCube);

        RectangularContainer testRect = testCube;   // casted to superclass
        /**
         * In the following line, Cube.toString() is still invoked because testRect is "actually" a Cube.
         * The behavior is different from how *fields* are accessed (e.g. testRect.foobar).
         */
        System.out.println(testRect.toString());
    }
}

// Private class based on section9.Cube
class Cube extends RectangularContainer {

    public Cube(double side) {
        super(side, side, side);
    }

    public String toString() {
        return String.format("Cube(side=%f)", width);   // width is accessible since it is `protected` in Rect'Container
    }
}