package section15;

/**
 * This class demonstrates all paths through a try-catch-finally statement.
 */
public class TryCatchFinally {

    // List of values to trigger different path in the try-catch-finally statement.
    static final int[] paths = { 101, 102, 103, 201, 202, 203, 301, 302, 303,
            411, 412, 413, 421, 422, 423, 431, 432, 433};

    static String explore(int val) throws Exception {
        try {
            System.out.print("inside try... ");

            switch (val / 100) {
                case 2: return "returned from try";
                case 3: throw new Exception("throw Exception inside try");
                case 4: throw new RuntimeException("throw RuntimeException inside try");
            }
        } catch (RuntimeException e) {
            System.out.print("inside catch... ");

            switch (val / 10 % 10) {
                case 2: return "returned from catch";
                case 3: throw new Exception("throw Exception inside catch");
            }
        } finally {
            System.out.print("inside finally... ");

            switch (val % 10) {
                case 2: return "returned from finally";
                case 3: throw new Exception("throw Exception inside finally");
            }
        }

        return "explore terminated normally with value = " + val;
    }

    public static void main(String[] argv) {
        for (int val : paths) {
            try {
                System.out.format("Explored value=%d... %s%n", val, explore(val));
            } catch (Exception e) {
                System.out.format("Caught Exception thrown inside explore(%d); message: %s%n", val, e.getMessage());
            }
        }
    }
}
