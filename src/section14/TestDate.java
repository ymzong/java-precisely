package section14;

/**
 * This class uses the MyDate class to determine the day of year for a given date.
 */
public class TestDate {

    public static void main(String[] argv) {
        MyDate d1 = new MyDate(2018, Month.APR, 1);
        System.out.println(d1.toString());
        System.out.println(d1.dayOfYear());

        MyDate d2 = new MyDate(2016, Month.JAN, 1);
        System.out.println(d2.toString());
        System.out.println(d2.dayOfYear());

        try {
            MyDate d3 = new MyDate(2100, Month.FEB, 29);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
