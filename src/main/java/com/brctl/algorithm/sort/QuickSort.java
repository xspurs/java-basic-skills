package com.brctl.algorithm.sort;

import java.util.Arrays;

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


    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 8, 2, 10, 11, 7, 6};
        System.out.println(Arrays.toString(sort(array, 0, array.length - 1)));
    }
}
