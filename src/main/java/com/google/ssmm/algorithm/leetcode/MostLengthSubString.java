package com.google.ssmm.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 示例 1:

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 **/
public class MostLengthSubString {
    /**
     * "pwwkew"
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int begin = 0;
        int end = 0;
        int length = 1;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        while (true) {
            if (begin > end || end >= s.length()) {
                length = Math.max(length, end - begin);
                break;
            }
            Integer temp = map.get(chars[end]);
            if (temp != null) {
                length = Math.max(length, end - begin);
                if(begin < temp+1){
                   begin = temp +1;
                }
            }
            map.put(chars[end], end);
            end++;
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.println(lengthOfLongestSubstring("caa"));
        System.out.println(lengthOfLongestSubstring("aa"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}


/*
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}

链接：https://leetcode-cn.com/problems/two-sum/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
 */

/*
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

链接：https://leetcode-cn.com/problems/two-sum/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
 */

