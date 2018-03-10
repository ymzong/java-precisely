package section9;

/**
 * Created by jzong on 3/9/18.
 */
public class ParameterArray {
    static int max(int v1, int v2) {
        System.out.println("Entering ParameterArray.max(int,int)");
        return Math.max(v1, v2);
    }

    // Parameter list argument can be empty, hence the need of v1.
    static int max(int v1, int... args) {
        System.out.println("Entering ParameterArray.max(int, int...)");
        int result = v1;

        for (int arg : args) {
            result = Math.max(result, arg);
        }
        return result;
    }

    public static void main(String[] argv) {
        // When deciding on which max() method to call, first try to match *without* parameter array
        System.out.format("Max of (2, 3): %d%n", max(2, 3));
        System.out.format("Max of (9): %d%n", max(9));
        System.out.format("Max of (9, 22, -8): %d%n", max(9, 22, -8));
    }
}
