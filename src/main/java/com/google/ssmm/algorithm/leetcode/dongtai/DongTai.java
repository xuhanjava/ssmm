package com.google.ssmm.algorithm.leetcode.dongtai;

public class DongTai {
    private int minCount = Integer.MAX_VALUE;

    //给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
    // 如果没有任何一种硬币组合能组成总金额，返回 -1。
    //
    // 
    //
    //示例 1:
    //
    //输入: coins = [1, 2, 5], amount = 11
    //输出: 3
    //解释: 11 = 5 + 5 + 1
    //示例 2:
    //
    //输入: coins = [2], amount = 3
    //输出: -1
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        int[] status = new int[amount+1];//第一个数组表示已有多少斤，第二个数组表示多少次
        for(int i =0;i<coins.length;i++){
            if (coins[i]<=amount){
                status[coins[i]] = 1;
            }
        }
        status[0] = 1;
        for (int j = 0; j <= amount; j++) {
            for (int x = 0; x < coins.length; x++) {
                if (j- coins[x] <0){
                    continue;
                }
                if (status[j] <=0){
                    continue;
                }
                if (j + coins[x] <= amount) {
                    if (coins[x] > amount) {
                        continue;
                    }
                    if (status[j + coins[x]] >0){
                        status[j + coins[x]] = Math.min(status[j + coins[x]],status[j] +1);
                    }else{
                        status[j + coins[x]] = status[j] +1;
                    }
                }
            }
        }
        if (status[amount] > 0) {
            return status[amount];
        }
        return -1;
    }

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        int[] status = new int[amount+1];
        for(int i =0;i<=amount;i++){
                status[i] = -1;
        }
        status[0] = 0;
        for(int x= 0;x<coins.length;x++){
            for( int j=0;j<=amount-coins[x];j++){
                if(status[j] >=0 ){
                    if(status[j+coins[x]] >=0){
                        status[j+coins[x]] = Math.min(status[j+coins[x]],status[j] +1);
                    }else{
                        status[j+coins[x]] = status[j] +1;
                    }
                }
            }
        }
        return status[amount];
    }


    public int coinChangeHuisu(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        huisu(coins, amount, 1, coins[0]);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    //[1,2147483647]
    //2

    public void huisu(int[] coins, int amount, int count, int sum) {
        if (sum > amount) {
            return;
        }
        if (sum == amount) {
            minCount = Math.min(count, minCount);
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            huisu(coins, amount, count + 1, sum + coins[i]);
        }
    }

}
