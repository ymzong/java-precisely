package section13;

import java.awt.*;

/* Enhance a ColoredPoint as drawable; implements ColoredDrawable since it also includes draw method */
public class ColoredDrawablePoint extends ColoredPoint implements ColoredDrawable {

    Color c;

    public ColoredDrawablePoint(int x, int y, Color c) {
        super(x, y, c);
    }

    public void draw(Graphics g) {
        System.out.println("Drawing ColoredDrawablePoint...");
        g.fillRect(x, y, 100, 100);
    }

}
