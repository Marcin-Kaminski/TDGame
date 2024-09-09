package objects;

import java.awt.geom.Point2D;

public class Projectile {

    private Point2D.Float pos;
    private int id, projectileType;
    private float xSpeed, ySpeed;
    private boolean active = true;

    public Projectile(float x, float y, float xSpeed, float ySpeed, int id, int projectileType) {
        pos = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.id = id;
        this.projectileType = projectileType;
    }

    public void move() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getProjectileType() {
        return projectileType;
    }
}
