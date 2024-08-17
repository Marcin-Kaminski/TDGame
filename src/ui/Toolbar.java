package ui;

import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Toolbar extends Bar {

    private MyButton buttonMenu, buttonSave;
    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    private Editing editing;

    public Toolbar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    private void initButtons() {
        buttonMenu = new MyButton("Menu", 2, 642, 100, 30);
        buttonSave = new MyButton("Save", 2, 674, 100, 30);

        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1);

        int i = 0;
        for(Tile tile : editing.getGame().getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i,  yStart, width, height, i));
            i++;
        }
    }

    public void drawButtons(Graphics g) {
        buttonMenu.draw(g);
        buttonSave.draw(g);

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
        return editing.getGame().getTileManager().getSprites(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        } else if (buttonSave.getBounds().contains(x, y)) {
            saveLevel();
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = editing.getGame().getTileManager().getTile(button.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    private void saveLevel() {
        editing.saveLevel();
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        buttonSave.setMouseOver(false);

        for (MyButton button : tileButtons) {
            button.setMouseOver(false);
        }

        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else if (buttonSave.getBounds().contains(x, y)) {
            buttonSave.setMouseOver(true);
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
        } else if (buttonSave.getBounds().contains(x, y)) {
            buttonSave.setMousePressed(true);
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
        buttonSave.resetBooleans();
        for (MyButton button : tileButtons) {
            button.resetBooleans();
        }
    }
}
