package com.google.ssmm.ThreadTest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池execute 后未来的某个时间才能真正的执行还有两个shutdown方法对正在处理的任务的处理
 * shutdown 关闭的话还是会把正在执行的任务和已经等待的任务停止了，但是不会接受新的任务
 * shutdownNow ：试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表
 *  无法保证能够停止正在处理的活动执行任务，但是会尽力尝试。例如，通过 Thread.interrupt() 来取消典型的实现，所以任何任务无法响应中断都可能永远无法终止。
 * Created by xuhan on 17-4-21.
 */
public class ExecutorServiceTest {
    private static final Integer THREAD_NUM=10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM/10);
        System.out.println("Main is begin!");
        for(int i = 0;i<THREAD_NUM;i++){
            ThreadTest thread = new ThreadTest(i);
            executorService.execute(thread);
        }
        System.out.println("Main is end");
        List<Runnable> runnables = executorService.shutdownNow();
    }
}

class ThreadTest implements Runnable{
    private int num;

    public ThreadTest(int num){
        this.num = num;
    }

    @Override public void run() {
        System.out.println("第"+num+"正在执行");
        try {
            Thread.sleep(300l);
        } catch (InterruptedException e) {
            System.out.println("第"+num+"阻塞了");
        }
        System.out.println("第"+num+"执行完毕");
    }
}
