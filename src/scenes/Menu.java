package scenes;

import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage img;

    private MyButton buttonPlay, buttonSettings, buttonQuit, buttonEdit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        buttonPlay = new MyButton("Play", x, y, w, h);
        buttonEdit = new MyButton("Edit", x, y + yOffset, w, h);
        buttonSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
        buttonQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (buttonPlay.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        } else if (buttonEdit.getBounds().contains(x, y)) {
            SetGameState(EDIT);
        } else if (buttonSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (buttonQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonPlay.setMouseOver(false);
        buttonEdit.setMouseOver(false);
        buttonSettings.setMouseOver(false);
        buttonQuit.setMouseOver(false);


        if (buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMouseOver(true);
        } else if (buttonEdit.getBounds().contains(x, y)) {
            buttonEdit.setMouseOver(true);
        } else if (buttonSettings.getBounds().contains(x, y)) {
            buttonSettings.setMouseOver(true);
        } else if (buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMousePressed(true);
        } else if (buttonEdit.getBounds().contains(x, y)) {
            buttonEdit.setMousePressed(true);
        } else if (buttonSettings.getBounds().contains(x, y)) {
            buttonSettings.setMousePressed(true);
        } else if (buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        buttonPlay.resetBooleans();
        buttonEdit.resetBooleans();
        buttonSettings.resetBooleans();
        buttonQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        buttonPlay.draw(g);
        buttonQuit.draw(g);
        buttonSettings.draw(g);
        buttonEdit.draw(g);
    }
}
