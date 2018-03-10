package section9;

import java.util.Arrays;

/**
 * This class demonstrates the effect of static / non-static field initialization.
 * Created by jzong on 3/9/18.
 */
public class StaticFieldInitialization {
    static double[] values = new double[6];

    /**
     * In this static initializer block, we fill values[] array with increasing random values and accumulate the sum.
     * Eventually, we normalize the array such that the last value is 1.0
     */
    static {
        double sum = 0;     // "local" variable
        for (int i = 0; i < 6; i++) {
            values[i] = sum += Math.random();
        }
        for (int i = 0; i < 6; i++) {
            values[i] /= values[5];
        }
    }

    static public void main(String[] argv) {
        System.out.println(Arrays.toString(values));
    }
}
