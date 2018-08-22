package com.brctl.interview;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import sun.java2d.pipe.OutlineTextRenderer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Sort map by its values, such as word count
 * <p>Use two ways: Plain & Stream
 * @author duanxiaoxing
 * @created 2018/8/21
 */
@Slf4j
public class MapSort {

    private static final Map<String, Integer> wordCounts;
    private static final Map<String, Integer> anotherWordCounts;

    static {
        wordCounts = Maps.newHashMap();
        wordCounts.put("hello", 5);
        wordCounts.put("apple", 8);
        wordCounts.put("java", 4);
        wordCounts.put("computer", 2);
        wordCounts.put("school", 10);

        anotherWordCounts = Maps.newHashMap();
        anotherWordCounts.put("hello", 5);
        anotherWordCounts.put("apple", 8);
        anotherWordCounts.put("java", 4);
        anotherWordCounts.put("computer", 2);
        anotherWordCounts.put("school", 10);
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValueInStream(Map<K, V> toSortedMap) {
        Map<K, V> result = Maps.newLinkedHashMap();
        toSortedMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }


    public static void main(String[] args) {
        // PLAIN
        List<Map.Entry<String, Integer>> arrayList = Lists.newArrayList(wordCounts.entrySet());
        // ascending order
        Collections.sort(arrayList, (o1, o2) -> o1.getValue() - o2.getValue());
        // accessOrder = false
        Map<String, Integer> linkedHashMap = Maps.newLinkedHashMap();
        for (Map.Entry<String, Integer> entry: arrayList) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        log.info("ascending order by values: {}", linkedHashMap);

        // STREAM
        Map<String, Integer> sortedUseStream = sortByValueInStream(anotherWordCounts);
        log.info("ascending order by values use stream: {}", sortedUseStream);
    }

}
