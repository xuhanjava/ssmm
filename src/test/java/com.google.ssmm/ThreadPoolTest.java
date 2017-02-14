package com.google.ssmm;

/**
 * Created by xuhan on 17-2-6.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //获得可用的处理器数目 从而用来设置线程池的大小
        System.out.println("count:" + Runtime.getRuntime().availableProcessors());
    }
}
