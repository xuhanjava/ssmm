package com.google.ssmm.algorithm.leetcode.tiku;

public class n9 {
    //判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    //
    //示例 1:
    //
    //输入: 121
    //输出: true
    //示例 2:
    //
    //输入: -121
    //输出: false
    //解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    //示例 3:
    //
    //输入: 10
    //输出: false
    //解释: 从右向左读, 为 01 。因此它不是一个回文数。
    //
    //链接：https://leetcode-cn.com/problems/palindrome-number
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = "" + x;
        int length = s.length();
        if(length == 1){
            return true;
        }
        // 2->0,1
        // 3->0,2
        // 4->1,2
        // 5->2,3
        int left = 0,right=length-1;
        while(left < right){
            if (s.charAt(left) == s.charAt(right)) {
                left++;right--;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(-0));
    }
}
