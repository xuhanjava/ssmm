package com.google.ssmm.algorithm.leetcode.dongtai;

public class BuyStock {
    //给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    //
    //如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
    //
    //注意：你不能在买入股票前卖出股票。
    //
    // 
    //
    //示例 1:
    //
    //输入: [7,1,5,3,6,4]
    //输出: 5
    //解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    //     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    //示例 2:
    //
    //输入: [7,6,4,3,1]
    //输出: 0
    //解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] status = new int[prices.length]; //索引是第几个买，值是第几个卖
        //赋予-1
        for (int i = 0; i < prices.length - 1; i++) { //买遍历一遍
            for (int j = i + 1; j < prices.length; j++) { //卖遍历一遍
                if (prices[j] > prices[i]) {
                    status[i] = Math.max(status[i], prices[j] - prices[i]);
                }
            }
        }
        int maxValue = 0;
        for (int i = 0; i < status.length; i++) {
            maxValue = Math.max(maxValue, status[i]);
        }
        return maxValue;
    }


    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     */

    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] array = new int[prices.length + 2][prices.length + 2];
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                array[i][j] = prices[j] - prices[i];
            }
        }
        //huisu(0,  1, 0, array, prices.length);
        dongtai(array, prices.length);
        return maxValue;
    }


    /**
     * @param i 第i天买，从0开始
     * @param j 第j天卖，从1开始
     * @param sum 总和
     */
    private Integer maxValue = 0;

    //{3,2,6,5,0,3,1};
    private void huisu(int i, int j, int sum, int[][] array, int length) {
        if (i >= length - 1 || j >= length) {
            maxValue = Math.max(maxValue, sum);
            return;
        }
        if (array[i][j] > 0) {
            huisu(j + 1, j + 2, sum + array[i][j], array, length);
        }
        huisu(i, j + 1, sum, array, length);
        huisu(i + 1, j + 1, sum, array, length);
    }

    //特别像动态规划
    //
    private void dongtai(int[][] array, int length) {
        //huisu(0,  1, 0, array, prices.length);
        int[][] status = new int[length + 2][length + 2];  //到这两个索引没有放入数据的状态
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 2; j++) {
                if (array[i][j] > 0) {
                    if (checkArray(j + 1, j + 2, length)) {
                        status[j + 1][j + 2] = Math.max(status[j + 1][j + 2], status[i][j] + array[i][j]);
                    }
                }
                if (checkArray(i + 1, j + 1, length)) {
                    status[i + 1][j + 1] = Math.max(status[i + 1][j + 1], status[i][j]);
                }
                if (checkArray(i, j + 1, length)) {
                    status[i][j + 1] = Math.max(status[i][j + 1], status[i][j]);
                }
            }
        }
        System.out.println(status[length - 1][length - 1]);
    }

    private void dongtai(int[] prices) {
        int status[][] = new int[prices.length][2];//0:持有的是现金，1：持有的是股票
        status[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            status[i][0] = Math.max(status[i - 1][0], status[i - 1][1] + prices[i]);
            status[i][1] = Math.max(status[i-1][1],status[i-1][0]-prices[i]);
        }
    }

    private boolean checkArray(int i, int j, int length) {
        if (i >= length || j >= length) {
            return false;
        }
        return true;
    }

}
