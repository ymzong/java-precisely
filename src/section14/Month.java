package section14;

/**
 * enum that represents month of year
 */
public enum Month {
    JAN(31), FEB(28), MAR(31), APR(30), MAY(31), JUN(30),
    JUL(31), AUG(31), SEP(30), OCT(31), NOV(30), DEC(31);     // List of enum values

    private final int length;                           // Instance field that represents length of a month
    public static final Month[] month = values();       // Use values() to get all enum values

    /* The following constructor will be invoked for every enum value along with its parameter(s) */
    private Month(int length) {
        this.length = length;
    }

    public int getLength(int year) {
        return (this == FEB && isLeapYear(year)) ? 29 : this.length;
    }

    public int toInt() {
        return ordinal() + 1;   // +1 such that Jan=1, Feb=2, etc.
    }

    public static Month fromInt(int m) {
        return month[m-1];
    }

    // Warning: This causes overflow for December!
    public Month next() {
        return fromInt(toInt() + 1);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
