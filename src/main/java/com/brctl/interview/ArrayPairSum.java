package com.brctl.interview;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Find a pair of numbers which their sum equals 20(or any other number)
 * <p>Time complexity: O(n)
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class ArrayPairSum {

    private static final int SUM = 20;

    private static void printSum(int[] input, int sum) {
        Map<Integer, Integer> pairs = Maps.newHashMap();
        for (int i = 0; i < input.length; i++) {
            if (pairs.containsKey(input[i])) {
                log.info("pairs sum to {}: {}, {}", sum, input[i], pairs.get(input[i]));
            } else {
                pairs.put(sum - input[i], input[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 0, 18, 4, 16, 20, 19, 16, 5, 8};
        printSum(input, SUM);
    }

}
