package section14;

/**
 * enum that represents day of a week.
 */
public enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;              // List of enum values

    public final static Day[] day = values();       // Cache all enum values in a list (for use in toDay and more)

    /* Trivial method that converts day of week to int (as its ordinal value). */
    public int toInt() {
        return ordinal();
    }

    /* Trivial method that converts an integer value (ordinal) back to Day enum */
    public Day toDay(int d) {
        return day[d];
    }

}
