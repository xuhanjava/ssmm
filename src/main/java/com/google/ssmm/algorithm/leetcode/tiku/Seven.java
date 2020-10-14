package com.google.ssmm.algorithm.leetcode.tiku;

public class Seven {
    //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    //
    //示例 1:
    //
    //输入: 123
    //输出: 321
    // 示例 2:
    //
    //输入: -123
    //输出: -321
    //示例 3:
    //
    //输入: 120
    //输出: 21
    //注意:
    //
    //假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2的31次方,  2的31次方 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
    //
    //链接：https://leetcode-cn.com/problems/reverse-integer

    //todo xuhan 代码没问题，就是溢出那块有问题得重新写下
    public static int reverse(int x) {
        int sum = 0;
        boolean flag = false;
        while (x != 0) {
            if (x % 10 == 0 && flag) {
                x /= 10;
            } else {
                if (Math.abs(sum) > (Integer.MAX_VALUE - Math.abs(x) % 10) / 10 ) {
                    return 0;
                }

                sum = sum * 10 + x % 10;
                flag = false;
            }
            x /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

}
