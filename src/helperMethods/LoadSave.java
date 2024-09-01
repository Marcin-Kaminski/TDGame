package helperMethods;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

            WriteToFile(newLevel, idArray, new PathPoint(0, 0), new PathPoint(0, 0));
        }
    }

    private static void WriteToFile(File f, int[] idArray, PathPoint start, PathPoint end) {
        try {
            PrintWriter printWriter = new PrintWriter(f);
            for (Integer i : idArray) {
                printWriter.println(i);
            }
            printWriter.println(start.getxCord());
            printWriter.println(start.getyCord());
            printWriter.println(end.getxCord());
            printWriter.println(end.getyCord());

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveLevel(String name, int[][] idArray, PathPoint start, PathPoint end) {
        File levelFile = new File("res/" + name + ".txt");

        if (levelFile.exists()) {
            WriteToFile(levelFile, Utilities.TwoDTo1DArray(idArray), start, end);

        } else {
            System.out.println("File: " + name + " does not exists! ");
            return;
        }
    }

    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                  list.add(Integer.parseInt(scanner.nextLine()));
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<PathPoint> getLevelPathPoints(String name) {
        File lvlFile = new File("res/" + name + ".txt");


        if (lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(400), list.get(401)));
            points.add(new PathPoint(list.get(402), list.get(403)));

            return points;

        } else {
            System.out.println("File: " + name + " does not exist!");
            return null;
        }
    }

    public static int[][] GetLevelData(String name) {
        File lvlFile = new File("res/" + name + ".txt");

        if (lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilities.ArrayListTo2Dint(list, 20, 20);

        } else {
            System.out.println("File: " + name + " does not exist!");
            return null;
        }
    }

}
