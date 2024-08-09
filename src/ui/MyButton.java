package ui;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class MyButton {

    private int x;
    private int y;
    private int width;
    private int height;
    private String text;

    private Rectangle bounds;

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    public void draw(Graphics g) {

        // body
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        // border
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);

        //text
        g.drawString(text, x + 10, y + 20);

    }

    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
