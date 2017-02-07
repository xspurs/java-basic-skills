package com.brctl.algorithm.sort;

import com.sun.scenario.effect.Merge;

import java.util.HashMap;

/**
 * Created by Duan Xiaoxing on 2017-01-24.
 */
public class Main {

    public static void main(String[] args) {

        int[] bubbleSortArray = {1, 3, 2, 6, 5, 4};
        int[] mergeSortArray = {1, 3, 2, 6, 5, 4};

        System.out.println("----------------------------------");
        // sort use bubble sort
        for(int i: BubbleSort.sort(bubbleSortArray)) {
            System.out.println(i);
        }

        System.out.println("----------------------------------");
        // sort use merge sort
        for(int i: MergeSort.sort(mergeSortArray)) {
            System.out.println(i);
        }

    }

}
