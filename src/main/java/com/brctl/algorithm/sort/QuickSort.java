package com.brctl.algorithm.sort;

/**
 * QuickSort
 * O(nlogn) ~ O(n^2), O(nlogn) as average
 * Created by Duan Xiaoxing on 2017-01-24.
 */
public class QuickSort {

    public static int[] sort(int[] toSort, int start, int end) {
        if(start < end) {
            int initValue = toSort[start];
            int low = start;
            int high = end;
            while(low < high) {
                while(low < high && toSort[high] >= initValue) {
                    high--;
                }
                toSort[low] = toSort[high];
                while(low < high && toSort[low] <= initValue) {
                    low++;
                }
                toSort[high] = toSort[low];
            }
            toSort[low] = initValue;
            // recursively sort
            sort(toSort, start, low - 1);
            sort(toSort, low + 1, end);
        }
        return toSort;
    }
}
