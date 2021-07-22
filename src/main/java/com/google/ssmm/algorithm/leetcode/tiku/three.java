package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.HashSet;
import java.util.Set;

public class three {
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if(chars == null || chars.length == 0){
            return 0;
        }
        Set<Character> hashSet = new HashSet<>();
        int begin=0,end = 0;
        int result = 0;
        while(begin <chars.length && end < chars.length){
            while(end < chars.length && !hashSet.contains(chars[end])){
                hashSet.add(chars[end]);
                end++;
            }
            result = Math.max(result,end - begin);
            hashSet.remove(chars[begin]);
            begin++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }










































    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }
    //复杂度分析
    //
    //时间复杂度：O(N)O(N)，其中 NN 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
    //
    //空间复杂度：O(|\Sigma|)O(∣Σ∣)，其中 \SigmaΣ 表示字符集（即字符串中可以出现的字符），|\Sigma|∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0, 128)[0,128) 内的字符，即 |\Sigma| = 128∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 |\Sigma|∣Σ∣ 个，因此空间复杂度为 O(|\Sigma|)O(∣Σ∣)。
    //
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
