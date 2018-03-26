package section12;

/**
 * This class covers a simple example of assert. Be sure that -enableassertions (-ea) flag is enabled for the JVM.
 */
public class AssertStatements {
    /* This mysterious method calculates the square root of an integer (result floored to int) */
    static int sqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("sqrt: negative argument");
        }

        int temp, y = 0, b = 0x8000, bshft = 15, v = x;
        do {
            if (v >= (temp = (y<<1)+b << bshft--)) {
                y += b;
                v -= temp;
            }
        } while ((b >>= 1) > 0);

        assert (long)y * y <= x && (long)(y+1) * (y+1) > x;   // verify the result before returning it
        return y;
    }

    public static void main(String[] argv) {
        System.out.format("sqrt(12345) = %d%n", sqrt(12345));
        System.out.format("sqrt(123456) = %d%n", sqrt(123456));
    }
}
