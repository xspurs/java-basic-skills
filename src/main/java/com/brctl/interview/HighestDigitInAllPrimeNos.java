package com.brctl.interview;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Find prime numbers & max key
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class HighestDigitInAllPrimeNos {

    public static void main(String[] args) {
        int r = 50;
        List<Integer> list = getAllPrimeNumbers(r);
        log.info("prime numbers list size: {}", list.size());
        List<Integer> finalListDigits = Lists.newArrayList();
        for (Integer in : list) {
            log.info("prime number: {}", in);
            finalListDigits.addAll(getDigit(in));
        }
        Map<Integer, Integer> frequencyMap = Maps.newHashMap();
        int maxValue = 0;
        int maxKey = 0;
        for (Integer i : finalListDigits) {
            if (frequencyMap.containsKey(i)) {
                frequencyMap.put(i, frequencyMap.get(i) + 1);
            } else {
                frequencyMap.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        log.info("{}", frequencyMap);
        log.info("max frequency key: {}", maxKey);
    }

    private static List<Integer> getAllPrimeNumbers(int r) {
        boolean[] mark = new boolean[r + 1];
        List<Integer> primeNumbers = new ArrayList<>();
        // initially mark all values till r as true
        for (int i = 0; i <= r; i++) {
            mark[i] = true;
        }
        int counter = 0;
        for (int p = 2; p * p <= r; p++) {
            log.info("counter: {}", ++counter);
            // this prime nos not yet changed
            if (mark[p]) {
                // then update all prime nos
                for (int i = p * 2; i <= r; i += p) {
                    mark[i] = false;
                }
            }
        }
        for (int i = 2; i <= r; i++) {
            if (mark[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    static List<Integer> getDigit(int n) {
        int loopNum = (int) Math.floor(Math.log10(n) + 1);
        List<Integer> digitList = Lists.newArrayList();
        while (loopNum != 0) {
            int k = n % 10;
            digitList.add(k);
            n = n / 10;
            loopNum--;
        }
        return digitList;
    }

}
