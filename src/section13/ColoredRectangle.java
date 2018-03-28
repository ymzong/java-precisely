package section13;

import java.awt.*;

/* Implements a Rectangle class based on ColoredDrawable interface */
public class ColoredRectangle implements ColoredDrawable {

    Color c;
    int x1, x2, y1, y2;

    public ColoredRectangle(int x1, int x2, int y1, int y2, Color c) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.c = c;
    }

    public Color getColor() {
        return c;
    }

    public void draw(Graphics g) {
        System.out.println("Drawing colored rectangle...");
        g.fillRect(x1, y1, x2-x1, y2-y1);
    }

}
