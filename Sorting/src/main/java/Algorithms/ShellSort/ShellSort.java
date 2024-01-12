package Algorithms.ShellSort;

public class ShellSort {
    //O(n2)

    // Похож на сортировку вставкой за исключением того, чтобы переставлять дальние друг от друга элементы
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {        // Start with a big gap, then reduce the gap
            for (int i = gap; i < n; i++) {  //Do a gapped insertion sort for this gap size.
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) { // shift earlier gap-sorted elements up until
                    arr[j] = arr[j - gap];                               // the correct location for a[i] is found
                }
                arr[j] = temp; //put temp (the original a[i]) in its correct location
            }
        }
    }
}
