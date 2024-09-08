package helperMethods;

import java.util.ArrayList;

public class Utilities {

    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArray = new int[ySize][xSize];

        for (int y = 0; y < newArray.length; y++) {
            for (int x = 0; x < newArray[y].length; x++) {
                int index = y * ySize + x;
                newArray[y][x] = list.get(index);
            }
        }

        return newArray;
    }

    public static int[] TwoDTo1DArray(int[][] twoArr) {
        int[] oneArr = new int[twoArr.length * twoArr[0].length];

        for (int y = 0; y < twoArr.length; y++) {
            for (int x = 0; x < twoArr[y].length; x++) {
                int index = y * twoArr.length + x;
                oneArr[index] = twoArr[y][x];
            }
        }

        return oneArr;
    }

    public static int GetHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1-x2);
        float yDiff = Math.abs(y1-y2);

        return (int) Math.hypot(xDiff, yDiff);
    }
}
