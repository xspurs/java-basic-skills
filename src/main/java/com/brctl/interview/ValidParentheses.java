package com.brctl.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Valid Parentheses
 * <p>{} [] () included
 * <p>valid example: {[]}
 * <p>invalid example: {[)}
 * @author duanxiaoxing
 * @created 2018/8/22
 */
@Slf4j
public class ValidParentheses {

    public boolean isValid(String s) {
        // use ArrayDeque substitute Stack, because ArrayDeque is faster
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('}', '{');
        pairs.put(']', '[');
        pairs.put(')', '(');
        List<Character> pushList = Arrays.asList('{', '[', '(');
        for (int i = 0; i < s.length(); i++) {
            if (pushList.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty() || stack.pop() != pairs.get(s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{[]}";
        log.info("isValid: {}", new ValidParentheses().isValid(s));
    }

}
