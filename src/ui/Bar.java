package ui;

import scenes.Playing;

import java.awt.*;

public class Bar {

    protected int x, y ,width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawButtonFeedback(Graphics g, MyButton button) {
        // MouseOver
        if(button.isMouseOver()) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }

        // Border
        g.drawRect(button.x, button.y, button.width, button.height);

        // mousePressed
        if (button.isMousePressed()) {
            g.drawRect(button.x + 1, button.y + 1, button.width - 2, button.height - 2);
            g.drawRect(button.x + 2, button.y + 2, button.width - 4, button.height - 4);
        }
    }
}
