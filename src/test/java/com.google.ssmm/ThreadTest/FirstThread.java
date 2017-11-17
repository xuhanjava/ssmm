package com.google.ssmm.ThreadTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/**
 * Created by xuhan on 16-12-17.
 */
public class FirstThread {

    volatile List<Integer> list = new ArrayList<>();

    /**
     * 线程池 场景：执行时间很短且数目很多的请求为之创建线程的消耗比较大.Executor(接口) ExecutorService子接口->AbstractExecutorService抽象类
     * -》ThreadPoolExecutor线程池 就是具体的实现类了。
     * 官方建议使用Executors而不是之间使用构造函数来创建ThreadPoolExecutor
     */
    @Test
    public void testA(){
        //无界的线程池.空闲的thread等待60s，没有的话就是 不调用这个thread了。
        ExecutorService service = Executors.newCachedThreadPool();

        //只是等待0秒，
        //ExecutorService service = Executors.newFixedThreadPool(2);

        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        //ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new Runnable() {

            @Override public void run() {
                System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"begin");
                try {
                    Thread.sleep(1000 * 3);
                    System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"end");
                } catch (InterruptedException e) {
                    System.out.println(123);
                }
            }
        });
        service.execute(new Runnable() {

            @Override public void run() {
                System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"begin");
                try {
                    Thread.sleep(1000 * 3);
                    System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"end");
                } catch (InterruptedException e) {
                    System.out.println(123);
                }
            }
        });
        /*try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void testVoliate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable r = ()->{
            list.add(12331);
            countDownLatch.countDown();
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println("main end!");
        countDownLatch.countDown();
        countDownLatch.await();
        System.out.println(list);

    }
}
