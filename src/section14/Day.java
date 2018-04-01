package section14;

/**
 * enum that represents day of a week.
 */
public enum Day {
    MON("Monday"), TUE("Tuesday"), WED("Wednesday"), THU("Thursday"),
    FRI("Friday"), SAT("Saturday"), SUN("Sunday");  // List of enum values

    private  String displayed;

    private Day(String s) {
        this.displayed = s;
    }

    public final static Day[] day = values();       // Cache all enum values in a list (for use in toDay and more)

    /* Trivial method that converts day of week to int (as its ordinal value). */
    public int toInt() {
        return ordinal();
    }

    /* Trivial method that converts an integer value (ordinal) back to Day enum */
    public Day toDay(int d) {
        return day[d];
    }

    @Override
    public String toString() {
        return displayed;
    }

}
