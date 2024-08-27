package managers;

import helperMethods.Constants;
import helperMethods.ImgFix;
import helperMethods.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helperMethods.Constants.Tiles.*;

public class TileManager {

    public Tile GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, BL_WATER_CORNER,
            TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER, L_WATER, TL_ISLE, TR_ISLE,
            BR_ISLE, BL_ISLE;
    public BufferedImage spriteAtlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Tile> roadsStraight = new ArrayList<>();
    public ArrayList<Tile> roadsCorners = new ArrayList<>();
    public ArrayList<Tile> waterCorners = new ArrayList<>();
    public ArrayList<Tile> beaches = new ArrayList<>();

    public ArrayList<Tile> islands = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();

    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
        tiles.add(WATER = new Tile(getAnimatedSprites(0, 0), id++, WATER_TILE));

        roadsStraight.add(ROAD_LR = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		roadsStraight.add(ROAD_TB = new Tile(ImgFix.getRotatedImg(getSprite(8, 0), 90), id++, ROAD_TILE));

        roadsCorners.add(ROAD_B_TO_R = new Tile(getSprite(7, 0), id++, ROAD_TILE));
		roadsCorners.add(ROAD_L_TO_B = new Tile(ImgFix.getRotatedImg(getSprite(7, 0), 90), id++, ROAD_TILE));
		roadsCorners.add(ROAD_L_TO_T = new Tile(ImgFix.getRotatedImg(getSprite(7, 0), 180), id++, ROAD_TILE));
		roadsCorners.add(ROAD_T_TO_R = new Tile(ImgFix.getRotatedImg(getSprite(7, 0), 270), id++, ROAD_TILE));


        waterCorners.add(BL_WATER_CORNER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(5, 0), 0), id++, WATER_TILE));
        waterCorners.add(TL_WATER_CORNER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(5, 0), 90), id++, WATER_TILE));
        waterCorners.add(TR_WATER_CORNER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(5, 0), 180), id++, WATER_TILE));
        waterCorners.add(BR_WATER_CORNER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(5, 0), 270), id++, WATER_TILE));

        beaches.add(T_WATER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(6, 0), 0), id++, WATER_TILE));
		beaches.add(R_WATER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(6, 0), 90), id++, WATER_TILE));
		beaches.add(B_WATER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(6, 0), 180), id++, WATER_TILE));
		beaches.add(L_WATER = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(6, 0), 270), id++, WATER_TILE));

		islands.add(TL_ISLE = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(4, 0), 0), id++, WATER_TILE));
		islands.add(TR_ISLE = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(4, 0), 90), id++, WATER_TILE));
		islands.add(BR_ISLE = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(4, 0), 180), id++, WATER_TILE));
		islands.add(BL_ISLE = new Tile(ImgFix.getBuildRotatedImg(getAnimatedSprites(0, 0), getSprite(4, 0), 270), id++, WATER_TILE));

        tiles.addAll(roadsStraight);
        tiles.addAll(roadsCorners);
        tiles.addAll(waterCorners);
        tiles.addAll(beaches);
        tiles.addAll(islands);
    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

    private void loadAtlas() {
        spriteAtlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getSprites(int id) {
        return tiles.get(id).getSprite();
    }

    public BufferedImage getAnimatedSprite(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }


    private BufferedImage getSprite(int xCord, int yCord) {
        return spriteAtlas.getSubimage(xCord * 32,yCord * 32,32,32);
    }


    private BufferedImage[] getAnimatedSprites(int xCord, int yCord) {
        BufferedImage[] arr = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = getSprite(xCord + i, yCord);
        }

        return arr;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }

    public ArrayList<Tile> getRoadsStraight() {
        return roadsStraight;
    }

    public ArrayList<Tile> getRoadsCorners() {
        return roadsCorners;
    }

    public ArrayList<Tile> getWaterCorners() {
        return waterCorners;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public boolean isSpriteAnimation(int spriteID) {
        return tiles.get(spriteID).isAnimation();
    }
}
