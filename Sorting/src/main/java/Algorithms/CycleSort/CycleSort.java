package Algorithms.CycleSort;

public class CycleSort {
    public static void cycleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int item = arr[i];
            int pos = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < item) {
                    pos++;
                }
            }

            if (pos == i) {
                continue;
            }

            while (item == arr[pos]) {
                pos++;
            }

            int temp = arr[pos];
            arr[pos] = item;
            item = temp;

            while (pos != i) {
                pos = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < item) {
                        pos++;
                    }
                }

                while (item == arr[pos]) {
                    pos++;
                }

                temp = arr[pos];
                arr[pos] = item;
                item = temp;
            }
        }
    }
}
