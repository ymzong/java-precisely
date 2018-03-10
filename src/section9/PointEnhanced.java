package section9;

/**
 * This class overloads the constructor of BasicPoint class
 * Created by jzong on 3/9/18.
 */
public class PointEnhanced {
    int x, y;

    /* Original Constructor */
    PointEnhanced(int x, int y) {
        this.x = x;
        this.y = y;
    }

    PointEnhanced(PointEnhanced p) {
        this(p.x, p.y);             // Syntax for invoking another constructor
    }

    public String toString() {
        return String.format("PointEnhanced(x=%d, y=%d)", x, y);
    }
}
