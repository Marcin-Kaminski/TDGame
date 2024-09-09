package managers;

import enemies.Enemy;
import helperMethods.Constants;
import helperMethods.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helperMethods.Constants.Towers.*;
import static helperMethods.Constants.Projectiles.*;

public class ProjectileManager {

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;


    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            proj_imgs[i] = atlas.getSubimage((7+i) * 32, 32, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDist = (int) Math.abs(t.getX() - e.getX());
        int yDist = (int) Math.abs(t.getY() - e.getY());
        int totDist = xDist + yDist;

        float xPer = (float) xDist / totDist;

        float xSpeed = xPer * Constants.Projectiles.getSpeed(type);
        float ySpeed = Constants.Projectiles.getSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *=-1;
        }
        if (t.getY() > e.getY()) {
            ySpeed *=-1;
        }

        projectiles.add(new Projectile(t.getX()+16, t.getY()+16, xSpeed, ySpeed, proj_id++, type));
    }

    public void update() {
        for (Projectile p : projectiles) {
            p.move();
        }
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {
            g.drawImage(proj_imgs[p.getProjectileType()], (int)p.getPos().x, (int)p.getPos().y, null);
        }
    }

    private int getProjectileType(Tower t) {
        return switch (t.getTowerType()) {
            case ARCHER -> ARROW;
            case CANNON -> BOMB;
            case WIZARD -> CHAINS;
            default -> 0;
        };
    }
}
