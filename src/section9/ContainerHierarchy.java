package section9;

import java.util.Arrays;

/**
 * We create and play with objects created from various Container classes defined in section9
 * Created by jzong on 3/3/18.
 */
public class ContainerHierarchy {
    public static void main(String[] argv) {
        // Initialize container objects
        BarrelContainer keg = new BarrelContainer(0.5, 3);
        Cube cube = new Cube(5.0);
        RectangularContainer rect = new RectangularContainer(3.0, 4.0, 5.0);
        LiquidContainer[] containers = new LiquidContainer[] { keg, cube, rect };

        // Display all capacities
        System.out.format("Capacity of the containers: %f, %f, %f.%n",
                keg.capacity(), cube.capacity(), rect.capacity());

        System.out.println("After filling in 30 units volume...");
        Arrays.stream(containers).forEach(elem -> elem.fill(30));       // Use Arrays.stream to apply fill() on each
        System.out.format("Current volume of containers: %f, %f, %f.%n",
                keg.contents, cube.contents, rect.contents);

        System.out.println("After filling in another 100 units of volume...");
        Arrays.stream(containers).forEach(elem -> elem.fill(100));
        System.out.format("Status of three containers:%n\t%s%n\t%s%n\t%s%n", // Uses toString overrides
                keg, cube, rect);
    }
}
