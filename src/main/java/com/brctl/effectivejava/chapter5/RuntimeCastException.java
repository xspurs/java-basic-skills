package com.brctl.effectivejava.chapter5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author duanxiaoxing
 * @created 2017/9/2
 */
public class RuntimeCastException {

    public static void main(String[] args) {
        List strings = new ArrayList();  // no generics
        strings.add("hello");
        strings.add(123);

        for (int i = 0; i < strings.size(); ++i) {
            System.out.println("element " + i + ": " + (String) strings.get(i));  // throw ClassCastException in runtime
        }
    }

//    public Set union(Set s1, Set s2) {
//        Set result = new HashSet(s1);
//
//    }

    static int numElementsInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }
}
