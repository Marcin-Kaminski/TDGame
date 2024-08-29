package ui;

import helperMethods.LoadSave;
import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Toolbar extends Bar {

    private MyButton buttonMenu, buttonSave;
    private Tile selectedTile;
    private Editing editing;
    private MyButton buttonPathStart, buttonPathEnd;
    private BufferedImage pathStart, pathEnd;
    private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();

    private MyButton buttonGrass, buttonWater, buttonRoadStraight, buttonRoadCorners, buttonWaterCorners,
            buttonBeaches, buttonIslands;
    private MyButton currentButton;
    private int currentIndex = 0;

    public Toolbar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initPathImages();
        initButtons();
    }

    private void initPathImages() {
        pathStart = LoadSave.getSpriteAtlas().getSubimage(7 * 32, 2 * 32, 32, 32);
        pathEnd = LoadSave.getSpriteAtlas().getSubimage(8 * 32, 2 * 32, 32, 32);
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
        int id = 0;

        buttonGrass = new MyButton("Grass", xStart, yStart, width, height, id++);
        buttonWater = new MyButton("Water", xStart + xOffset , yStart, width, height, id++);
        initMapButton(buttonRoadStraight, editing.getGame().getTileManager().getRoadsStraight(), xStart, yStart, xOffset, width, height, id++);
        initMapButton(buttonRoadCorners, editing.getGame().getTileManager().getRoadsCorners(), xStart, yStart, xOffset, width, height, id++);
        initMapButton(buttonWaterCorners, editing.getGame().getTileManager().getWaterCorners(), xStart, yStart, xOffset, width, height, id++);
        initMapButton(buttonBeaches, editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, width, height, id++);
        initMapButton(buttonIslands, editing.getGame().getTileManager().getIslands(), xStart, yStart, xOffset, width, height, id++);

        buttonPathStart = new MyButton("PathStart", xStart, yStart + xOffset, width, height, id++);
        buttonPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, width, height, id++);
    }

    private void initMapButton(MyButton button, ArrayList<Tile> list, int x, int y, int xOffset, int width, int height, int id) {
        button = new MyButton("",x + xOffset * id, y, width, height, id);
        map.put(button, list);
    }

    public void rotateSprite() {
        currentIndex++;
        if (currentIndex >= map.get(currentButton).size()) {
            currentIndex = 0;
        }
        selectedTile = map.get(currentButton).get(currentIndex);
        editing.setSelectedTile(selectedTile);
    }

    public void drawButtons(Graphics g) {
        buttonMenu.draw(g);
        buttonSave.draw(g);

        drawPathButtons(g, buttonPathStart, pathStart);
        drawPathButtons(g, buttonPathEnd, pathEnd);

        drawNormalButton(g, buttonGrass);
        drawNormalButton(g, buttonWater);
        drawSelectedTile(g);
        drawMapButtons(g);
    }

    private void drawPathButtons(Graphics g, MyButton button, BufferedImage img) {

        g.drawImage(img, button.x, button.y, button.width, button.height, null);
        drawButtonFeedback(g, button);
    }

    public void drawNormalButton(Graphics g, MyButton button) {
        g.drawImage(getButtonImg(button.getId()), button.x, button.y, button.width, button.height, null);
        drawButtonFeedback(g, button);
    }

    public void drawMapButtons(Graphics g) {
        //sprites
        for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
            MyButton button = entry.getKey();
            BufferedImage img = entry.getValue().getFirst().getSprite();

            g.drawImage(img, button.x, button.y, button.width, button.height, null);
            drawButtonFeedback(g, button);
        };

    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(550, 650, 50, 50);
        }
    }

    private void drawButtonFeedback(Graphics g, MyButton button) {
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

    public BufferedImage getButtonImg(int id) {
        return editing.getGame().getTileManager().getSprites(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        } else if (buttonSave.getBounds().contains(x, y)) {
            saveLevel();
        } else if (buttonWater.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(buttonWater.getId());
            editing.setSelectedTile(selectedTile);
        } else if (buttonGrass.getBounds().contains(x, y)) {
        selectedTile = editing.getGame().getTileManager().getTile(buttonGrass.getId());
        editing.setSelectedTile(selectedTile);
        } else if (buttonPathStart.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathStart, -1, -1);
            editing.setSelectedTile(selectedTile);
        } else if (buttonPathEnd.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathEnd, -2, -2);
            editing.setSelectedTile(selectedTile);
        } else {
            for (MyButton button : map.keySet()) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = map.get(button).getFirst();
                    editing.setSelectedTile(selectedTile);
                    currentButton = button;
                    currentIndex = 0;
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
        buttonGrass.setMouseOver(false);
        buttonWater.setMouseOver(false);
        buttonPathStart.setMouseOver(false);
        buttonPathEnd.setMouseOver(false);

        for (MyButton button : map.keySet()) {
           button.setMouseOver(false);
        }

        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else if (buttonSave.getBounds().contains(x, y)) {
            buttonSave.setMouseOver(true);
        } else if (buttonGrass.getBounds().contains(x, y)) {
            buttonGrass.setMouseOver(true);
        } else if (buttonWater.getBounds().contains(x, y)) {
            buttonWater.setMouseOver(true);
        } else if (buttonPathStart.getBounds().contains(x, y)) {
            buttonPathStart.setMouseOver(true);
        } else if (buttonPathEnd.getBounds().contains(x, y)) {
            buttonPathEnd.setMouseOver(true);
        } else {
            for (MyButton button : map.keySet()) {
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
        } else if (buttonGrass.getBounds().contains(x, y)) {
            buttonGrass.setMousePressed(true);
        } else if (buttonWater.getBounds().contains(x, y)) {
            buttonWater.setMousePressed(true);
        }  else if (buttonPathStart.getBounds().contains(x, y)) {
            buttonPathStart.setMousePressed(true);
        } else if (buttonPathEnd.getBounds().contains(x, y)) {
            buttonPathEnd.setMousePressed(true);
        } else {
            for (MyButton button : map.keySet()) {
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
        buttonGrass.resetBooleans();
        buttonWater.resetBooleans();
        buttonPathStart.resetBooleans();
        buttonPathEnd.resetBooleans();
        for (MyButton button : map.keySet()) {
            button.resetBooleans();
        }
    }
}
