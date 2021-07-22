package com.google.ssmm.algorithm.leetcode.tiku;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class n139 {
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static boolean wordBreak1(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (String item : wordDict) {
            if (s.startsWith(item)) {
                if (wordBreak1(s.substring(item.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < wordDict.size(); j++) {
                if (s.substring(0, i + 1).endsWith(wordDict.get(j))) {
                    if (dp[i + 1 - wordDict.get(j).length()]) {
                        dp[i + 1] = true;
                    }
                }
            }

        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("abcd", Lists.newArrayList("ab", "cd")));
    }

}
