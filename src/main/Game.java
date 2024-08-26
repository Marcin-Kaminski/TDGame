package main;

import helperMethods.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;

public class Game extends JFrame implements Runnable {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private GameScreen gameScreen;
    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Settings settings;
    private Playing playing;
    private Editing editing;
    private TileManager tileManager;

    public Game() {
        initClasses();
        createDefaultLevel();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gameScreen);
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel("new_level", arr);
    }

    private void initClasses() {
        tileManager = new TileManager();
        gameScreen = new GameScreen(this);
        render = new Render(this);
        menu = new Menu(this);
        settings = new Settings(this);
        playing = new Playing(this);
        editing = new Editing(this);
    }


    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    public void updateGame() {
        switch (GameStates.gameState) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                break;
            case SETTINGS:
                break;
            case EDIT:
                break;
            default:
                break;
        }

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
                lastUpdate = now;
                updates++;
                updateGame();
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

    public Editing getEditing() {
        return editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
