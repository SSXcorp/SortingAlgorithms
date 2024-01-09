package Algorithms.BucketSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {
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
}
