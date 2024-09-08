package managers;

import enemies.*;
import helperMethods.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helperMethods.Constants.Direction.*;
import static helperMethods.Constants.Enemies.*;
import static helperMethods.Constants.Tiles.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    public ArrayList<Enemy> enemies = new ArrayList<>();
//    private float speed = 0.5f;
    private PathPoint start, end;
    private int hpBarWidth = 20;


    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        this.start = start;
        this.end = end;
        addEnemy(ORC);
        addEnemy(BAT);
        addEnemy(KNIGHT);
        addEnemy(WOLF);
        loadEnemyImgs();
    }

    public void update() {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                updateEnemyMove(e);
            }
        }
    }

    public void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            // keep moving in same direction
            e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
        } else if (isAtEnd(e)) {
            System.out.println("lives lost");
        } else {
            setNewDirectionAndMove(e);
            // find new direction
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        // move into the current tile till 100%
        int xCord = (int)(e.getX()) / 32;
        int yCord = (int)(e.getY()) / 32;

        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if (isAtEnd(e)) {
            return;
        }

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int)(e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
                e.move(GetSpeed(e.getEnemyType()), UP);
            } else {
                e.move(GetSpeed(e.getEnemyType()), DOWN);
            }
        } else {
            int newX = (int)(e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
            if (getTileType(newX, (int)e.getY()) == ROAD_TILE) {
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            } else {
                e.move(GetSpeed(e.getEnemyType()), DOWN);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }
        e.setPos(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {
//        return e.getX() == end.getxCord() * 32 && e.getY() == end.getyCord() * 32;
        if (e.getX() == end.getxCord() * 32)
            if (e.getY() == end.getyCord() * 32)
                return true;
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndWidth(int dir, int enemyType) {
        return switch (dir) {
            case LEFT -> -GetSpeed(enemyType);
            case RIGHT -> GetSpeed(enemyType) + 32;
            default -> 0;
        };
    }

    private float getSpeedAndHeight(int dir, int enemyType) {
        return switch (dir) {
            case UP -> -GetSpeed(enemyType);
            case DOWN -> GetSpeed(enemyType) + 32;
            default -> 0;
        };
    }

    public void addEnemy(int enemyType) {
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;

        switch (enemyType) {
            case ORC:
                enemies.add(new Orc(x, y, 0));

                break;
            case BAT:
                enemies.add(new Bat(x, y, 0));

                break;
            case KNIGHT:
                enemies.add(new Knight(x, y, 0));

                break;
            case WOLF:
                enemies.add(new Wolf(x, y, 0));

                break;
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }
    }

    public void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + 16 - (getNewBarWidth(e) / 2), (int)e.getY() - 10, getNewBarWidth(e), 3);
    }

    private int getNewBarWidth(Enemy e) {
        return (int) (hpBarWidth * e.getHealthBarFloat());
    }

    public void drawEnemy(Enemy enemy, Graphics g) {
        g.drawImage(enemyImgs[enemy.getEnemyType()], (int)enemy.getX(), (int)enemy.getY(), null);
    }

    public void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for(int i = 0; i < 4; i++) {
            enemyImgs[i] = atlas.getSubimage(i * 32,32,32,32);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
