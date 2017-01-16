package com.google.ssmm.ThreadTest;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuhan on 16-12-22.
 */
public class ThreadTest4 {
    public static void main1(String[] args) {
        //新的任务塞进队列中塞不进去
        //当线程数量大于corePoolSize时，在没有超过制定的时间内是不从线程池中将空闲线程删除的，如果超过这个时间的话才会删除。若为0的话则任务执行完成过后
        //立即从队列中删除

        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
        /*
        shutdown
        public void shutdown()
        按过去执行已提交任务的顺序发起一个有序的关闭，但是不接受新任务。如果已经关闭，则调用没有其他作用。
        抛出：
        SecurityException - 如果安全管理器存在并且关闭此 ExecutorService 可能操作某些不允许调用者修改的线程（因为它没有 RuntimePermission ("modifyThread")），或者安全管理器的 checkAccess 方法拒绝访问。
        shutdownNow

        public List<Runnable> shutdownNow()
        尝试停止所有的活动执行任务、暂停等待任务的处理，并返回等待执行的任务列表。在从此方法返回的任务队列中排空（移除）这些任务。
        并不保证能够停止正在处理的活动执行任务，但是会尽力尝试。 此实现通过 Thread.interrupt() 取消任务，所以无法响应中断的任何任务可能永远无法终止。

        返回：
        从未开始执行的任务的列表。
         */
        pool.shutdown();
        pool.shutdownNow();
        pool.execute(new Runnable() {
            @Override public void run() {
                System.out.println("1");
                System.out.println(Thread.currentThread().getName());
            }
        });
        System.out.println(
            "activeCount:" + pool.getActiveCount() + " taskCount:" + pool.getTaskCount() + " queueLength:" + pool
                .getQueue().size());
        pool.execute(new Runnable() {
            @Override public void run() {
                System.out.println(2);
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
        pool.execute(new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + "begin");
                try {
                    Thread.sleep(1000l);
                    System.out.println(Thread.currentThread().getName() + "end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.execute(new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + "begin");
                try {
                    Thread.sleep(1000l);
                    System.out.println(Thread.currentThread().getName() + "end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("main end");
        pool.shutdown();
        pool.execute(new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + "begin");
                try {
                    Thread.sleep(1000l);
                    System.out.println(Thread.currentThread().getName() + "end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
