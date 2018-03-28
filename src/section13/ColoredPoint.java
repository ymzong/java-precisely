package section13;

import section9.BasicPoint;

import java.awt.*;

/* Enhance a BasicPoint object with Color; implements Colored interface by defining getColor method */
public class ColoredPoint extends BasicPoint implements Colored {

    Color c;

    /* Constructor for ColorPoint has to exist because otherwise it would call the default constructor, super().
     * However, super(), or BasicPoint() does not exist.
     * See: https://stackoverflow.com/q/7187799
     */
    public ColoredPoint(int x, int y, Color c) {
        super(x, y);
        this.c = c;
    }

    public Color getColor() {       // must be public since it's part of Colored interface
        return c;
    }

}
