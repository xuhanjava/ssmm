package com.google.ssmm.algorithm.leetcode.dongtai;

public class CheapeastPrice {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prev = new int[n];
        for (int i = 0; i < flights.length; i++) {
            if (flights[i][0] == src) {
                prev[flights[i][1]] = flights[i][2];
            }
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < flights.length; j++) {
                if (prev[flights[j][0]] > 0 && prev[flights[j][1]] >0) {
                    prev[flights[j][1]] = Math.min(prev[flights[j][1]],prev[flights[j][0]] + flights[j][2]);
                }else if(prev[flights[j][0]] > 0 && prev[flights[j][1]] == 0 ){
                    prev[flights[j][1]] = prev[flights[j][0]] + flights[j][2];
                }
            }
        }
        //prev[node] =
        return prev[dst] == 0?-1:prev[dst];
    }

    public static void main(String[] args) {
        //n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        //src = 0, dst = 2, k = 1
        //int [][] edges = {{0,1,100},{1,2,100},{0,2,500}};
        int [][] edges1 = {{1,2,10},{2,0,7},{1,3,8},{4,0,10},{3,4,2},{4,2,10},{0,3,3},{3,1,6},{2,4,5}};
       // System.out.println(findCheapestPrice(3,edges,0,2,0));
       // System.out.println(findCheapestPrice(5,edges1,0,4,1));
        int [][] edges2 = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        // System.out.println(findCheapestPrice(3,edges,0,2,0));
        System.out.println(findCheapestPrice(4,edges2,0,3,1));

        //4
        //[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
        //0
        //3
        //1


    }
    //5
    //[[1,2,10],[2,0,7],[1,3,8],[4,0,10],[3,4,2],[4,2,10],[0,3,3],[3,1,6],[2,4,5]]
    //0
    //4
    //1
}
