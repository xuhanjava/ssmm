package com.google.ssmm.algorithm.leetcode;

import com.google.ssmm.algorithm.leetcode.dongtai.LengthOfLIS;
import com.google.ssmm.algorithm.leetcode.dongtai.OneAndZero;
import com.google.ssmm.algorithm.leetcode.tiku.LFUCache;
import com.google.ssmm.algorithm.leetcode.tiku.n155;
import com.google.ssmm.algorithm.leetcode.tiku.water;

public class Test {
    public static void main(String[] args) {
//        DongTai t = new DongTai();
//        int [] array ={7,1,5,3,6,4};
//        int [] array1 = {7,6,4,3,1};
//        int [] array2 ={1,2,3,4,5};
//        int [] array3 = {3,2,6,5,0,3};
//        int [] array4 = {2};
        //System.out.println(t.coinChangeHuisu(array,2));
        //System.out.println(t.coinChange1(array4,3));
        //System.out.println(t.coinChange2(array4,3));
//        BuyStock bs = new BuyStock();
//        //System.out.println(bs.maxProfit(array));
//        //System.out.println(bs.maxProfit(array1));
//
//        //System.out.println(bs.maxProfit1(array));
//        //System.out.println(bs.maxProfit1(array3));
//
//        List<Integer> list = Arrays.asList(12, 3, 4);
//        boolean remove = list.remove(new Integer(1));
//        System.out.println(list);
//        System.out.println(remove);
        //testDongtai();
        //testLFUCache();
        //lengthOfLIS();
        water w = new water();
        w.maxWater(new int[]{3,1,2,5,2,4});
    }

    public static void testMinStack(){
        n155 s = new n155();
        s.push(1);
        s.push(-2);
        s.push(1);
        s.pop();
        System.out.println(s.getMin());
    }

    public static void testLFUCache(){
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,3);
        lfuCache.put(1,3);
        lfuCache.put(3,3);
        lfuCache.put(4,1);
    }

    /**
     * * 474. 一和零
     *  * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
     *  *
     *  * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     *  *
     *  * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     *  *
     *  * 注意:
     *  *
     *  * 给定 0 和 1 的数量都不会超过 100。
     *  * 给定字符串数组的长度不会超过 600。
     *  * 示例 1:
     *  *
     *  * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
     *  * 输出: 4
     *  *
     *  * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
     *  * 示例 2:
     *  *
     *  * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
     *  * 输出: 2
     *  *
     *  * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
     */
    public static void testDongtai(){
        OneAndZero t = new OneAndZero();
        String [] array ={"10", "0001", "111001", "1", "0"};
        String [] array1 = {"10", "0", "1"};

        System.out.println(t.findMaxForm(array,5,3));
        System.out.println(t.findMaxForm(array1,1,1));
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 示例:
     *
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     *
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static void lengthOfLIS() {
        int [] array1 = {10,9,2,5,3,7,101,18};
        LengthOfLIS t = new LengthOfLIS();
        System.out.println(t.lengthOfLIS(array1));
    }

    //
}
