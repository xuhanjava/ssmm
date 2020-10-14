package com.google.ssmm.algorithm.leetcode.dongtai;

public class Huisu {
    static int maxW = Integer.MIN_VALUE;
    static int maxE = Integer.MIN_VALUE;
    static int maxD1 = Integer.MIN_VALUE;
    static int countW = 0;
    static int countE = 0;
    static int countD1 = 0;
    static int minHuisu = Integer.MAX_VALUE;
    static int countDD1 =0;
    static int countDD2 = 0;


    public static void f(int i, int cw, int[] items, int n, int w) {
        countW++;
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    public static void main(String[] args) {
//        int[] array = {7, 9, 3, 10, 223};
//        f(0, 0, array, array.length, 99);
//        f1(0, 0, array, array.length, 99);
//        System.out.println(maxW + "_" + countW);
//        System.out.println(maxE + "_" + countE);
//        System.out.println(f2(array, array.length, 99) + "_" + countD1);
//        System.out.println("minValue:" + f4(array, array.length, 239));
        int[][] array = {{3, 32, 2,53}, {33, 11, 2,33}, {32, 122, 23,13},{31,33,11,32}};
        System.out.println("true:" + minDistDP(array, array.length) +":count->"+countDD1);

        huisuDp1(array,0,0,array.length,0);
        System.out.println("huisu:"+minHuisu+":count->"+countDD2);
    }

    public static void f1(int i, int cw, int[] items, int n, int w) {
        countE++;
        if (cw == w || i == n) {
            if (cw > maxE) maxE = cw;
            return;
        }
        if (cw + items[i] <= w) {
            cw += items[i];
            f1(i + 1, cw, items, n, w);
            cw -= items[i];
        }
        f1(i + 1, cw, items, n, w);
    }

    public static int f22(int[] weight, int n, int w) {
        boolean[][] status = new boolean[n][w + 1];
        status[0][0] = true;
        if (weight[0] <= w) {
            status[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i - 1][j]) {
                    status[i][j] = status[i - 1][j];
                    if (weight[i] + j >= w) {
                        continue;
                    }
                    status[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) {
            if (status[n - 1][i] == true) return i;
        }
        return 0;
    }

    //动态规划第一版
    public static int f2(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true;
        //第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= w; ++j) {
                countD1++;
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                    if (j + weight[i] < w) {
                        states[i][j + weight[i]] = true;
                    }
                }
            }
//            for (int j = 0; j <= w - weight[i]; ++j) {
//                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
//                countD1 ++;
//            }
        }
        for (int i = w; i >= 0; --i) {
            countD1++;
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

    //动态规划第二版
    public static int f3(int[] weight, int n, int w) {
        boolean[] status = new boolean[w + 1];
        status[0] = true;
        if (weight[0] <= w) {
            status[weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w - weight[i]; j++) { //这得从大到小的排，要不然就变成了每个物品可以装入多次,就是硬币的那个逻辑了
                if (status[j]) {
                    if (weight[i] + j < w) {
                        status[j + weight[i]] = true;
                    }
                }
            }
        }
        for (int i = w; i >= 0; --i) {
            if (status[i] == true) return i;
        }
        return 0;
    }

    //动态规划第三版
    public static int f4(int[] weight, int n, int w) {
        int length = 300 * w + 1;
        boolean[][] status = new boolean[n][length];
        status[0][0] = true;
        if (weight[0] <= w) {
            status[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i - 1][j]) {
                    status[i][j + weight[i]] = true;
                    status[i][j] = true;
                }
            }
        }
        for (int i = w; i < length; i++) {
            if (status[n - 1][i] == true && i >= w) {
                return i;
            }
        }
        return 0;
    }

    //最短路径
    public static int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { //初始化states的第-行数据
            countDD1++;
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { //初始化states的第-列数据
            countDD1++;
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                countDD1++;
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);

            }
        }
        return states[n - 1][n - 1];
    }

    public static int minDist1DP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        //todo xuhan初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                states[i][j] = 0;
            }
        }
        states[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //不需要这样做，因为status[i][j]肯定是从 status[i-1][j] 或者status[i][j-1]算出来的
                if (states[i - 1][j] > 0) {
                    if (states[i][j] == 0) {
                        states[i][j] = states[i - 1][j] + matrix[i][j];
                    } else {
                        states[i][j] = Math.min(states[i - 1][j] + matrix[i][j], states[i][j]);
                    }
                    if (j + 1 < n) {
                        if (states[i - 1][j + 1] == 0) {
                            states[i - 1][j + 1] = states[i - 1][j] + matrix[i - 1][j + 1];
                        } else {
                            states[i - 1][j + 1] = Math.min(states[i - 1][j] + matrix[i - 1][j + 1], states[i - 1][j + 1]);
                        }
                    }
                }
            }
        }
        return states[n - 1][n - 1];
    }

    //错误例子，终于懂了为什么回溯方法f（上面），为什么要先放入下一个，而不是先先塞入再放入下一个；因为递归跳出来，还是塞入的，不是新的递归逻辑；详细见下面的例子
    public static void huisuDp(int[][] matrix, int i, int j, int maxDepth, int sum) {
        if (i >= maxDepth - 1 && j >= maxDepth - 1) {
            minHuisu = Math.min(minHuisu, sum);
            return;
        }
        if (i < maxDepth-1) {
            sum += matrix[i][j];
            huisuDp(matrix,i+1,j,maxDepth,sum);
            System.out.println(i);
        }
        if (j<maxDepth-1){
            sum += matrix[i][j+1];
            huisuDp(matrix,i,j,maxDepth,sum);
            System.out.println(j);
        }
    }


    //总结下回溯的逻辑：
    // 1。执行到最后一个判断
    // 2。多种可能一一实现
    //先是a,然后是b……;先按照a执行，如果然后跳出来执行b，记住执行b的时候要把本次执行a的结果给消除了

    //回溯与动态规划的区别就是：去掉重复的
    public static void huisuDp1(int[][] matrix, int i, int j, int maxDepth, int sum) {
        countDD2++;
        if (i >= maxDepth-1 && j >= maxDepth-1) {
            minHuisu = Math.min(minHuisu, sum + matrix[i][j]);
            return;
        }
        if (i < maxDepth-1) {
            huisuDp1(matrix,i+1,j,maxDepth,sum+matrix[i][j]);
        }
        if (j <maxDepth -1){
            huisuDp1(matrix,i,j+1,maxDepth,sum +matrix[i][j]);
        }
    }

}
