package com.google.ssmm.algorithm.other;

import java.util.HashMap;
import java.util.Map;

public class ZeroOneBag {
    public int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    public int[] weight = {2, 2, 4, 6, 3}; //物品重量
    public int n = 5; //物品个数
    public int w = 16; //背包承受的最大重量
    public Map<String,Integer> map = new HashMap();

    public void f(int i, int cw) { //调用f(0,0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装 第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); //选择第i个物品
        }
    }

    public void put(int i,int cw){
        System.out.println("put="+i+",cw="+cw);
        String key = i+"_"+cw;
        if (map.containsKey(key)){
            map.put(key,map.get(key) +1);
        }else{
            map.put(key,1);
        }
        if (i +1 > n || cw == w){
            if(cw >maxW) {
                maxW = cw;
            }
            return;
        }
        if (cw + weight[i] <= w){
            put(i+1,cw+weight[i]);
        }
        put(i+1,cw);
    }
}
