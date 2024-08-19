package helperMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {

    // Rotate

    public static BufferedImage getRotatedImg(BufferedImage img, int rotAngle) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), width / 2, height / 2);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

    //Img layering
    public static BufferedImage buildImage(BufferedImage[] imgs) {
        int width = imgs[0].getWidth();
        int height = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : imgs) {
            g2d.drawImage(img, 0, 0, null);
        }
        g2d.dispose();

        return newImg;
    }

    // Rotate second img only
    public static BufferedImage getBuildRotatedImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
        int width = imgs[0].getWidth();
        int height = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i = 0; i < imgs.length; i++) {
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), width / 2, height / 2);
            }
            g2d.drawImage(imgs[i], 0, 0, null);
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(-rotAngle), width / 2, height / 2);
            }
        }
        g2d.dispose();

        return newImg;
    }
}
