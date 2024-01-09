package Algorithms.CountingSort;

public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = getMax(arr);
        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        for (int value : arr) {
            count[value]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
