package helperMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void CreateFile() {
        File txtFile = new File("res/testTextFile.txt");

        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateLevel(String name, int[] idArray) {
        File newLevel = new File("res/" + name + ".txt");

        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            WriteToFile(newLevel, idArray);
        }
    }

    private static void WriteToFile(File f, int[] idArray) {

        try {
            PrintWriter printWriter = new PrintWriter(f);
            for (Integer i : idArray) {
                printWriter.println(i);
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ReadFromFile() {
        File txtFile = new File("res/testTextFile.txt");
        try {
            Scanner scanner = new Scanner(txtFile);
            while (scanner.hasNextLine()) {
                  System.out.println(scanner.nextLine());
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
