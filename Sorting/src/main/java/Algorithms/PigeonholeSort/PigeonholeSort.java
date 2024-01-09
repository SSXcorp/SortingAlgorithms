package Algorithms.PigeonholeSort;

import java.util.ArrayList;
import java.util.List;

public class PigeonholeSort {
    public static void pigeonholeSort(int[] arr) {
        int min = getMinValuePigeon(arr);
        int max = getMaxValuePigeon(arr);
        int range = max - min + 1;

        List<List<Integer>> pigeonholes = new ArrayList<>(range);
        for (int i = 0; i < range; i++) {
            pigeonholes.add(new ArrayList<>());
        }

        for (int value : arr) {
            pigeonholes.get(value - min).add(value);
        }

        int index = 0;
        for (List<Integer> pigeonhole : pigeonholes) {
            for (int value : pigeonhole) {
                arr[index++] = value;
            }
        }
    }

    private static int getMinValuePigeon(int[] arr) {
        int min = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private static int getMaxValuePigeon(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
