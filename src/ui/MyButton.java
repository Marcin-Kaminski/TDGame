package ui;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class MyButton {

     public int x, y, width, height, id;
    private String text;

    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }


    public void draw(Graphics g) {

        // body
        drawBody(g);

        // border
        drawBorder(g);

        //text
        drawText(g);

    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if (mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    private void drawBody(Graphics g) {
        if (mouseOver) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
