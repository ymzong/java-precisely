package section9;

/**
 * This basic class represents a 2D coordinate.
 *
 * Created by jzong on 2/27/18.
 */
public class BasicPoint {
    // Two non-static fields
    public int x, y;

    // One constructor
    public BasicPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // non-static method
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    // non-static method
    public String toString() {
        return String.format("BasicPoint(x = %d, y = %d)", x, y);
    }
}
