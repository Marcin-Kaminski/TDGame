package scenes;

import main.Game;

import java.awt.image.BufferedImage;

public class GameScene {

    protected Game game;

    protected int animationIndex;
    protected int tick;
    protected final int ANIMATION_SPEED = 25;

    public GameScene(Game game) {
        this.game = game;

    }

    protected boolean isAnimation(int spriteID) {
        return game.getTileManager().isSpriteAnimation(spriteID);
    }

    public Game getGame() {
        return game;
    }

    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4) {
                animationIndex = 0;
            }
        }
    }

    protected BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprites(spriteId);
    }

    protected BufferedImage getAnimatedSprite(int spriteId, int animationIndex) {
        return game.getTileManager().getAnimatedSprite(spriteId, animationIndex);
    }

}
