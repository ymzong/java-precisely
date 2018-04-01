package section14;

/* This class represents a calendar date with helper methods */
public class MyDate {

    private final int yy, dd;
    private final Month mm;

    public MyDate(int yy, Month mm, int dd) throws IllegalArgumentException {
        if (!validateInput(yy, mm, dd)) {
            throw new IllegalArgumentException(
                    String.format("Invalid date supplied: %4d-%02d-%02d", yy, mm.toInt(),dd));
        }
        this.yy = yy;
        this.mm = mm;
        this.dd = dd;
    }

    /* Validates the supplied yy-mm-dd; return true iff valid */
    public static boolean validateInput(int yy, Month mm, int dd) {
        return 1 <= dd && dd <= mm.getLength(yy);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    /* "Magic method" that returns the day-of-year for the given date */
    public int dayOfYear() {
        int monthno = mm.toInt() - 1;
        int monthadjust =
                monthno > 1 ? (27 + 4 * monthno) / 10 - (isLeapYear(yy) ? 1 : 0) : 0;
        return dd - 1 + 31 * monthno - monthadjust;
    }

    @Override
    public String toString() {
        return String.format("%4d-%02d-%02d", yy, mm.toInt(),dd);
    }
}
