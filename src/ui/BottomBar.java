package ui;

import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class BottomBar {

    private int x, y ,width, height;
    private Playing playing;
    private MyButton buttonMenu;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        buttonMenu = new MyButton("Menu", 2, 642, 100, 30);

        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1);

        int i = 0;
        for(Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i,  yStart, width, height, i));
            i++;
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        buttonMenu.draw(g);

        drawTileButtons(g);
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton button : tileButtons) {
            g.drawImage(getButtonImg(button.getId()), button.x, button.y, button.width, button.height, null);
        }
    }

    public BufferedImage getButtonImg(int id) {
        return playing.getTileManager().getSprites(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y))
            buttonMenu.setMousePressed(true);

    }

    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();

    }
}
