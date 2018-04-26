package section21;

import java.util.ArrayList;

/**
 * This example demonstrates the implementation and use of a generic logger.
 */
public class GenericLogger {

    public static void main(String[] argv) {
        Log<String> myLogger = new Log<>(new String[3]);

        myLogger.add("22:34:47 - Elasticsearch OOM");
        System.out.println(myLogger.getLast());

        myLogger.add("22:34:56 - Elasticsearch healthcheck running...");
        myLogger.setLast("22:34:58 - Elasticsearch healthcheck failed");
        System.out.println(myLogger.getAll());

        myLogger.add("22:34:59 - Restarting Elasticsearch");
        myLogger.add("22:35:02 - Elasticsearch running");
        myLogger.add("22:35:12 - Elasticsearch healthcheck succeeded");
        System.out.println(myLogger.getAll());      // Only get last three entries
    }
}

/**
 * Generic Log class that implements a rotating logger.
 */
class Log<T> {

    private static int loggerCount = 0;     // Count of all Log instances created

    private final T[] entries;      // rotating array that stores log entries
    private final int size;         // size of the rotating array
    private int entryCount;         // number entries logged so far

    /* Initializes fields of the logger */
    public Log(T[] entries) {
        this.entries = entries;
        this.size = entries.length;
        loggerCount++;
    }

    /* Appends a log entry */
    public void add(T entry) {
        entries[(entryCount++) % size] = entry;
    }

    /* Returns the last log entry (if exists) */
    public T getLast() {
        if (entryCount == 0) {
            throw new IllegalStateException("Cannot get from empty logger!");
        }
        return entries[(entryCount - 1) % size];
    }

    /* Overwrites the last log entry */
    public void setLast(T entry) {
        if (entryCount == 0) {
            throw new IllegalStateException("Cannot get from empty logger!");
        }
        entries[(entryCount - 1) % size] = entry;
    }

    /* Returns all existing log entries, in backwards order */
    public ArrayList<T> getAll() {
        int retainedCount = Math.min(entryCount, size);
        ArrayList<T> result = new ArrayList<>(retainedCount);

        for (int i = 0; i < retainedCount; i++) {
            result.add(entries[(entryCount - 1 - i) % size]);
        }

        return result;
    }

}