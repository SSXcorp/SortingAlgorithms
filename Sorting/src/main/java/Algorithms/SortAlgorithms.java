package Algorithms;

import java.util.*;

public class SortAlgorithms {

    //Shell sort =======================================================================
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



    //Radix sort =======================================================================
    //It has the complexity of O(n+k), where k is the maximum element of the input array

    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Bucket sort =======================================================================
    //

    public static void bucketSort(int[] arr) {
        int maxVal = getMaxValue(arr);
        int numBuckets = (int) Math.ceil(Math.sqrt(maxVal));
        List<List<Integer>> buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }

        for (int value : arr) {
            int bucketIndex = (int) Math.floor((value * numBuckets) / (double) (maxVal + 1));
            buckets.get(bucketIndex).add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                arr[index++] = value;
            }
        }
    }

    private static int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Counting sort =======================================================================

    public static void countingSort(int[] arr) {
        int max = getMaxCounting(arr);
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

    private static int getMaxCounting(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Comb sort =======================================================================

    public static void combSort(int[] arr) {
        int n = arr.length;
        int gap = n;

        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = getNextGap(gap);

            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    swap(arr, i, i + gap);
                    swapped = true;
                }
            }
        }
    }

    private static int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        return Math.max(gap, 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Pigeonhole sort =======================================================================

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

    // Cycle sort =======================================================================

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

    // Gnome sort =======================================================================

    public static void gnomeSort(int[] arr) {
        int index = 0;
        while (index < arr.length) {
            if (index == 0 || arr[index] >= arr[index - 1]) {
                index++;
            } else {
                swapGnome(arr, index, index - 1);
                index--;
            }
        }
    }

    private static void swapGnome(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Quick sort =======================================================================

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swapQ(arr, i, j);
            }
        }
        swapQ(arr, i + 1, high);
        return i + 1;
    }

    private static void swapQ(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Quick sort =======================================================================

    public void cocktailSort(int a[])
    {
        boolean swapped = true;
        int start = 0;
        int end = a.length;

        while (swapped == true)
        {
            // reset the swapped flag on entering the
            // loop, because it might be true from a
            // previous iteration.
            swapped = false;

            // loop from bottom to top same as
            // the bubble sort
            for (int i = start; i < end - 1; ++i)
            {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }

            // if nothing moved, then array is sorted.
            if (swapped == false)
                break;

            // otherwise, reset the swapped flag so that it
            // can be used in the next stage
            swapped = false;

            // move the end point back by one, because
            // item at the end is in its rightful spot
            end = end - 1;

            // from top to bottom, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; i--)
            {
                if (a[i] > a[i + 1])
                {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }

            // increase the starting point, because
            // the last stage would have moved the next
            // smallest number to its rightful spot.
            start = start + 1;
        }
    }
}
