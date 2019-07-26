package com.google.ssmm.algorithm.leetcode;

/*
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

 */
public class ZConvert {
    public static String convert(String s, int numRows) {
        if(s == null || s.length()==0){
            return "";
        }
        char[] chars = s.toCharArray();
        if(numRows == 0){
            return "";
        }
        if(numRows == 1){
            return s;
        }
        char[][] array = new char[numRows][chars.length];
        int shuhangNum = 0;
        int count = 0;
        while(true){
            for(int i =0;i<numRows && count<chars.length;i++,count++){
                array[i][shuhangNum] = chars[count];
            }
            if(count>=chars.length){
                break;
            }
            for(int i = 1;i<numRows -1&& count<chars.length;i++,count++){
                array[numRows-1 - i][i+shuhangNum] = chars[count];
            }
            if(count>=chars.length){
                break;
            }
            shuhangNum=shuhangNum +numRows -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<array.length;i++){
            for(int j =0;j<array[i].length;j++){
                if(array[i][j] != 0){
                    sb.append(array[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(convert("LEETCODEISHIRING",4));
        System.out.println(convert("AB",1));
    }
}

/*
    方法一：按行排序
思路

通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。

算法

我们可以使用 {min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。

从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。

只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。

C++Java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}
复杂度分析

时间复杂度：O(n)O(n)，其中 n == \text{len}(s)n==len(s)
空间复杂度：O(n)O(n)
方法二：按行访问
思路

按照与逐行读取 Z 字形图案相同的顺序访问字符串。

算法

首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...

对于所有整数 kk，

行 00 中的字符位于索引 k \; (2 \cdot \text{numRows} - 2)k(2⋅numRows−2) 处;
行 \text{numRows}-1numRows−1 中的字符位于索引 k \; (2 \cdot \text{numRows} - 2) + \text{numRows} - 1k(2⋅numRows−2)+numRows−1 处;
内部的 行 ii 中的字符位于索引 k \; (2 \cdot \text{numRows}-2)+ik(2⋅numRows−2)+i 以及 (k+1)(2 \cdot \text{numRows}-2)- i(k+1)(2⋅numRows−2)−i 处;
C++Java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}
复杂度分析

时间复杂度：O(n)O(n)，其中 n == \text{len}(s)n==len(s)。每个索引被访问一次。
空间复杂度：O(n)O(n)。对于 C++ 实现，如果返回字符串不被视为额外空间，则复杂度为 O(1)O(1)。

 */