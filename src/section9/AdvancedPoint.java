package section9;

import java.util.ArrayList;

/**
 * This class, an extension of BasicPoint, represents 2D coordinates as well as keeping track
 * of all points created so far.
 *
 * Created by jzong on 2/27/18.
 */
public class AdvancedPoint {
    /**
     *  This static field keeps track of all points created so far.
     *  It is shared by all AdvancedPoint objects
     */
    static ArrayList<AdvancedPoint> allPoints = new ArrayList<AdvancedPoint>();

    /* Two non-static fields that represent coordinates of each point */
    int x, y;

    /* Constructor that returns a new AdvancedPoint object as well as adding (a reference of) it to allPoints list */
    public AdvancedPoint(int x, int y) {
        allPoints.add(this);                // `this` represents the current instantiation of the class
        this.x = x;
        this.y = y;
    }

    /* Non-static method that modifies the coordinates */
    void move(int dx, int dy) {
        x += dx;                            // x is the field (this.x)
        y += dy;                            // ditto
    }

    /* Non-static method that overrides the behavior of .toString() */
    public String toString() {
        return String.format("AdvancedPoint(x = %d, y = %d)", x, y);
    }

    /* Non-static method that returns the index of an AdvancedPoint object in the class-wide list */
    int getIndex() {
        return allPoints.indexOf(this);     // again, `this` represents the invoking object; indexOf() searches in List
    }

    /* Static method that returns the size of allPoints list */
    static int getSize() {
        return allPoints.size();
    }

    /* Static method that returns an AdvancedPoint based on its index */
    static AdvancedPoint getPoint(int idx) {
        return allPoints.get(idx);
    }
}
