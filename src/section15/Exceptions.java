package section15;

import java.io.*;

/**
 * This class demonstrates the use of Exceptions and try-catch-finally statement
 */
public class Exceptions {

    static final String[] weekdays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    /* Given a day of week, return the index in the constant array above */
    static int findWeekday(String val)  throws WeekdayException {
        for (int i = 0; i < weekdays.length; i++) {
            if (weekdays[i].equals(val)) {
                return i;
            }
        }
        throw new WeekdayException(val);    //  value not found
    }

    static void decodeWeekday(String day) {
        try {
            int mon = findWeekday(day);
            System.out.format("Found Monday at index %d; next day is %s.%n", mon, weekdays[mon+1]);
        } catch (WeekdayException e) {
            System.out.format("WeekdayException: %s%n", e.getMessage());
        } catch (Exception e) {
            System.out.format("Other Exception: %s%n", e);
        } finally {
            System.out.println("Exiting decodeWeekday()");
        }
    }

    /* Use try-finally statement to read a double from file, and close always the file */
    static void parseFileTryFinally() throws IOException {
        BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter file name: ");
        String filename = breader.readLine();

        BufferedReader freader = new BufferedReader(new FileReader(filename));

        // Close the file regardless right after reading from it
        try {
            double v1 = Double.parseDouble(freader.readLine());
            System.out.format("Read from file: %f%n", v1);
        } finally {
            System.out.println("Closing the file...");
            freader.close();
        }
    }

    /* Use try-with-resources to read a double from file; file is automatically closed */
    static void parseFileTryResources() throws IOException {
        BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter file name: ");
        String filename = breader.readLine();

        try (BufferedReader freader = new BufferedReader(new FileReader(filename))) {
            double v1 = Double.parseDouble(freader.readLine());
            System.out.format("Read from file: %f%n", v1);
        } finally {
            System.out.println("File already closed automatically!");
        }
    }

    public static void main(String[] argv) {
        decodeWeekday("Monday");
        decodeWeekday("Funday");    // To trigger WeekdayException
        decodeWeekday("Sunday");    // To trigger ArrayOutOfBounds

        try {
            parseFileTryFinally();
            parseFileTryResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/* Trivial Exception class that contains a message String */
class WeekdayException extends Exception {
    public WeekdayException(String value) {
        super("Illegal weekday: " + value); // invokes superclass constructor with info string
    }
}
