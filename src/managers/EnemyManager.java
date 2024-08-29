package managers;

import enemies.*;
import helperMethods.LoadSave;
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
    private float speed = 0.5f;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        addEnemy(0 * 32, 17 * 32, ORC);
        addEnemy(1 * 32, 17 * 32, BAT);
        addEnemy(2 * 32, 17 * 32, KNIGHT);
        addEnemy(3 * 32, 17 * 32, WOLF);
        loadEnemyImgs();
    }

    public void update() {
        for (Enemy e : enemies) {
            updateEnemyMove(e);
        }
    }

    public void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir()));
        int newY = (int)(e.getY() + getSpeedAndHeight(e.getLastDir()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            // keep moving in same direction
            e.move(speed, e.getLastDir());
        } else if (isAtEnd(e)) {
            // reached the end
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

        fixEnemyOffsetTile(e,dir, xCord, yCord);

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int)(e.getY() + getSpeedAndHeight(UP));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
                e.move(speed, UP);
            } else {
                e.move(speed, DOWN);
            }
        } else {
            int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir()));
            if (getTileType(newX, (int)e.getY()) == ROAD_TILE) {
                e.move(speed, RIGHT);
            } else {
                e.move(speed, DOWN);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
//            case LEFT:
//                if (xCord > 0) {
//                    xCord--;
//                }
//                break;
//            case UP:
//                if (yCord > 0) {
//                    yCord--;
//                }
//                break;
            case RIGHT:
                if (yCord < 19) {
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
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndWidth(int dir) {
        return switch (dir) {
            case LEFT -> -speed;
            case RIGHT -> speed + 32;
            default -> 0;
        };
    }

    private float getSpeedAndHeight(int dir) {
        return switch (dir) {
            case UP -> -speed;
            case DOWN -> speed + 32;
            default -> 0;
        };
    }

    public void addEnemy(int x, int y, int enemyType) {
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
            drawEnemy(e, g);
        }
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
}
