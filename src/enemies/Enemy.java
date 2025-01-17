package enemies;

import helperMethods.Constants.Enemies;

import java.awt.*;
import static helperMethods.Constants.Direction.*;

public abstract class Enemy {

    protected float x, y;
    protected Rectangle bounds;
    protected int health, maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;

    public Enemy(float x, float y, int ID, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
        lastDir = -1;
        setStartHealth();
    }

    public float getHealthBarFloat() {
        return health /(float) maxHealth;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (health <= 0) {
            alive = false;
        }
    }

    private void setStartHealth() {
        health = Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }
    public void move(float speed, int dir) {
        lastDir = dir;
        switch (dir) {
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }

        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void setPos(int x, int y) {
        // Don't use for move, this is for position fix
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }
}
