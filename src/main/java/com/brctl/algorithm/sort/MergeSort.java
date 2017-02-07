package com.brctl.algorithm.sort;

/**
 * MergeSort
 * O(nlogn)
 * Created by Duan Xiaoxing on 2017-02-07.
 */
public class MergeSort {

    // sort int array
    public static int[] sort(int[] toSort) {
        int length = toSort.length;
        int[] returnArray = new int[length];
        mergeSort(toSort, 0, length - 1, returnArray);
        return returnArray;
    }

    // divide and merge the array recursively
    private static void mergeSort(int a[], int first, int last, int temp[]) {
        if(first < last) {
            int mid = (first + last) / 2;
            mergeSort(a, first, mid, temp);
            mergeSort(a, mid + 1, last, temp);
            mergeArray(a, first, mid, last, temp);
        }
    }

    // merge sorted array
    private static void mergeArray(int a[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1;
        int k = 0;
        while(i <= mid && j <= last) {
            if(a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while(i <= mid) {
            temp[k++] = a[i++];
        }
        while(j <= last) {
            temp[k++] = a[j++];
        }
        for(i = 0; i < k; i++) {
            a[first + i] = temp[i];
        }
    }

}
