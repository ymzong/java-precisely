package section13;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/* This class uses the Colored* classes defined in the package */
public class ShapeDrawingApp extends Applet {

    /* Given an array of Colored, print out their colors */
    static void printColors(Colored[] cs) {
        for (Colored elem : cs) {
            System.out.println(elem.getColor().toString());
        }
    }

    /* Draws the ColoredDrawable by using its interface methods */
    static void draw(Graphics g, ColoredDrawable[] cds) {
        for (ColoredDrawable cd : cds) {
            g.setColor(cd.getColor());      // get the color of Drawable with getColor()
            cd.draw(g);                     // draw the shape with draw(Graphics)
        }
    }

    /* This method will be invoked by the Applet to draw shapes */
    @Override
    public void paint(Graphics g) {
        ColoredDrawablePoint p1 = new ColoredDrawablePoint(12, 12, new Color(12, 12, 12));
        ColoredRectangle r1 = new ColoredRectangle(105, 305, 205, 355, new Color(33, 75, 152));
        ColoredDrawable[] shapes = new ColoredDrawable[] { p1, r1 };

        printColors(shapes);
        draw(g, shapes);
    }
}
