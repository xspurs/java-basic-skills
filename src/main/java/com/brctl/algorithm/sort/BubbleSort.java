package com.brctl.algorithm.sort;

/**
 * BubbleSort
 * O(n^2)
 * Created by Duan Xiaoxing on 2017-01-24.
 */
public class BubbleSort {

    // sort int arrry
    public static int[] sort(int[] toSort) {
        int length = toSort.length;
        for(int i = 0; i < length; i++) {
            int temp;
            for(int j = i + 1; j < length; j++) {
                if(toSort[i] > toSort[j]) {
                    temp = toSort[i];
                    toSort[i] = toSort[j];
                    toSort[j] = temp;
                }
            }
        }
        return toSort;
    }

}
