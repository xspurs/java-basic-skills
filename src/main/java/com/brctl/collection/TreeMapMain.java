package com.brctl.collection;

import com.brctl.collection.domain.Student;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Duan Xiaoxing on 2016-12-08.
 */
public class TreeMapMain {

    public static void main(String[] args) {
        Map<Long, Student> studentMap = new TreeMap<>();
        studentMap.put(2L, new Student("Tom", 88));
        studentMap.put(1L, new Student("Lucy", 90));
        studentMap.put(3L, new Student("Jerry", 95));

        for(Map.Entry<Long, Student> student: studentMap.entrySet()) {
            System.out.println(student.getValue().toString());
        }

        System.out.println("===== ===== ===== =====");
        SortedMap<Long, Student> sortedMap =  ((TreeMap) studentMap).tailMap(2L);
        for(Map.Entry entry: sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

}
