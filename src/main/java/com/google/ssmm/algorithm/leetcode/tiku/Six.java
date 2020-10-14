package com.google.ssmm.algorithm.leetcode.tiku;

public class Six {
    //将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    //
    //比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    //
    //L   C   I   R
    //E T O E S I I G
    //E   D   H   N
    //之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
    //
    //请你实现这个将字符串进行指定行数变换的函数：
    //
    //string convert(string s, int numRows);
    //示例 1:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 3
    //输出: "LCIRETOESIIGEDHN"
    //示例 2:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 4
    //输出: "LDREOEIIECIHNTSG"
    //解释:
    //
    //L     D     R
    //E   O E   I I
    //E C   I H   N
    //T     S     G
    //
    //链接：https://leetcode-cn.com/problems/zigzag-conversion
    public static String convert(String s, int numRows) {
        char [][] array = new char[numRows][s.length()+1/2];
        int i=0,j=0;
        int iRange = 1;
        if ("".equals(s)){
            return s;
        }
        if(numRows == 0){
            return "";
        }
        if(numRows == 1){
            return ""+s.charAt(0);
        }
        for(int index =0;index <s.length();index++){
            array[i][j] = s.charAt(index);
            if (iRange == 1){
                i++;
            }
            if (iRange == -1){
                i--;
            }
            if (i == numRows){
                i -=2;
                iRange = -iRange;
            }
            if (i == -1){
                i+=2;
                iRange = -iRange;
            }
            if (index /(numRows-1) %2 == 1){
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(i=0;i<array.length;i++){
            for(j=0;j<array[i].length;j++){
                if(array[i][j] != 0){
                    sb.append(array[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("S",2));
    }
}
