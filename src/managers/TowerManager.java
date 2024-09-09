package managers;

import enemies.Enemy;
import helperMethods.LoadSave;
import helperMethods.Utilities;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Towers.*;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImgs();
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        towerImgs = new BufferedImage[3];
        for(int i = 0; i < 3; i++) {
            towerImgs[i] = atlas.getSubimage((4 + i) *32, 32, 32, 32);
        }
    }

    public void draw(Graphics g) {
        for (Tower tower : towers) {
            g.drawImage(towerImgs[tower.getTowerType()], tower.getX(), tower.getY(), null);
        }
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    public void update() {
        attackEnemyIfClose();
    }

    private void attackEnemyIfClose() {
        for (Tower t : towers) {
            for (Enemy e : playing.getEnemyManager().getEnemies()) {
                if (e.isAlive()) {
                    if (isEnemyInRange(t, e)) {
                        playing.shootEnemy(t, e);
                        // shoot enemy
                    } else {
                        // do nothing
                    }
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = Utilities.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());

        return range < t.getRange();
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void setTowerImgs(BufferedImage[] towerImgs) {
        this.towerImgs = towerImgs;
    }
}
