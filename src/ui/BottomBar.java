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

    private Tile selectedTile;

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
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(550, 650, 50, 50);
        }
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton button : tileButtons) {

            //sprites
            g.drawImage(getButtonImg(button.getId()), button.x, button.y, button.width, button.height, null);

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

    public BufferedImage getButtonImg(int id) {
        return playing.getTileManager().getSprites(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = playing.getTileManager().getTile(button.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        for (MyButton button : tileButtons) {
            button.setMouseOver(false);
        }

        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMousePressed(true);
                    return;
                }
            }
        }

    }

    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();
        for (MyButton button : tileButtons) {
            button.resetBooleans();
        }
    }
}
