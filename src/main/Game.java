package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private GameScreen gameScreen;
    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Settings settings;
    private Playing playing;

    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
        initClasses();
        add(gameScreen);
        pack();

        setVisible(true);
    }

    private void initClasses() {
        gameScreen = new GameScreen(this);
        render = new Render(this);
        menu = new Menu(this);
        settings = new Settings(this);
        playing = new Playing(this);
    }


    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
     }

    private void updateGame() {
//        System.out.println("Game Updated");
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {

            now = System.nanoTime();
            // Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

            // check UPS and FPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                updates = 0;
                frames = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Settings getSettings() {
        return settings;
    }

    public Playing getPlaying() {
        return playing;
    }

}
