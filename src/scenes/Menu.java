package scenes;

import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;

    private MyButton buttonPlay;
    private MyButton buttonSettings;
    private MyButton buttonQuit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {

        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        buttonPlay = new MyButton("Play", x, y, w, h);
        buttonSettings = new MyButton("Settings", x, y + yOffset, w, h);
        buttonQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    @Override
    public void render(Graphics g) {

        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (buttonPlay.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonPlay.setMouseOver(false);
        if (buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        buttonPlay.setMouseOver(false);
        if (buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        buttonPlay.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        buttonPlay.draw(g);
        buttonQuit.draw(g);
        buttonSettings.draw(g);
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    private int getRandomInt() {
        return random.nextInt(100);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
