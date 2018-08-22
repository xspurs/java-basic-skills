package com.brctl.interview;

import lombok.extern.slf4j.Slf4j;

/**
 * Longest Common Prefix
 * 最长公共前缀
 * <p>Example: ["flow", "flower", "flight"] -->> "fl"
 * @author duanxiaoxing
 * @created 2018/8/22
 */
@Slf4j
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String commonPrefix = "";
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        for (int i = 0; i < minLength; i++) {
            char tempChar = ' ';
            boolean isBreak = false;
            for (int j = 0; j < strs.length; j++) {
                if (tempChar != ' ' && tempChar != strs[j].charAt(i)) {
                    isBreak = true;
                    break;
                } else {
                    tempChar = strs[j].charAt(i);
                }
                if (j == strs.length - 1) {
                    commonPrefix += strs[j].charAt(i);
                }
            }
            if (isBreak)
                break;
        }
        return commonPrefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight"};
        String result = new LongestCommonPrefix().longestCommonPrefix(strs);
        log.info("result: {}", result);
    }

}
