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
}
