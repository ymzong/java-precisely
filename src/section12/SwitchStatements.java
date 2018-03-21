package section12;

import static section12.DayOfWeek.FRI;

/**
 * This class demonstrates the use of switch statements, especially on Enum and String
 * Created by jzong on 3/20/18.
 */
public class SwitchStatements {

    /* Switch on String is new in Java 7 */
    static void switchOnString() {
        String countryName = "Great Britain";

        switch (countryName) {
        case "North America":
            System.out.println("Area Code 1");
            return;
        case "Denmark":
            System.out.println("Area Code 45");
            return;
        case "Great Britain":
            System.out.println("Area Code 44");
            return;
        default:
            System.out.println("Area Code Not Found");
        }
    }

    /* Switch on Enum type */
    static void switchOnEnum() {
        DayOfWeek dow = FRI;

        switch (dow) {
            case MON:
            case TUE:
            case WED:
            case THU:
                System.out.println("Ehh workday...");
                break;
            case FRI:
                System.out.println("Yay Friday");
                break;
            case SAT:
                System.out.println("Saturday!");
                break;
            case SUN:
                System.out.println("Weekend is over :(");
                break;
        }
    }

    public static void main(String[] argv) {
        switchOnString();
        switchOnEnum();
    }
}

/* Trivial Enum representing day of week */
enum DayOfWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN
}