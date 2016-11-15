package com.google.ssmm;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://my.oschina.net/lujianing/blog/787745。不错
 * MQ中平滑关闭监听器队列
 * Created by xuhan on 16-11-22.
 */
public class MsgClient {

    //模拟消息队列订阅者 同时4个线程处理
    private static final ThreadPoolExecutor THREAD_POOL = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    //模拟消息队列生产者
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
    //用于判断是否关闭订阅
    private static volatile boolean isClose = false;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        producer(queue);
        consumer(queue);
    }

    //模拟消息队列生产者
    private static void producer(final BlockingQueue  queue){
        //每200毫秒向队列中放入一个消息
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(new Runnable() {
            public void run() {
                queue.offer("");
            }
        }, 0L, 200L, TimeUnit.MILLISECONDS);
    }

    //模拟消息队列消费者 生产者每秒生产5个   消费者4个线程消费1个1秒  每秒积压1个
    private static void consumer(final BlockingQueue queue) throws InterruptedException {
        while (!isClose){
            getPoolBacklogSize();
            //从队列中拿到消息
            final String msg = (String)queue.take();
            //放入线程池处理
            if(!THREAD_POOL.isShutdown()) {
                THREAD_POOL.execute(new Runnable() {
                    public void run() {
                        try {
                            //System.out.println(msg);
                            TimeUnit.MILLISECONDS.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    //查看线程池堆积消息个数
    private static long getPoolBacklogSize(){
        long backlog = THREAD_POOL.getTaskCount()- THREAD_POOL.getCompletedTaskCount();
        System.out.println(String.format("[%s]THREAD_POOL backlog:%s",System.currentTimeMillis(),backlog));
        return backlog;
    }

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName != null && osName.indexOf("window") == -1) {
            //注册linux kill信号量  kill -12
            Signal sig = new Signal("USR2");
            Signal.handle(sig, new SignalHandler() {
                @Override
                public void handle(Signal signal) {
                    System.out.println("收到kill消息，执行关闭操作");
                    //关闭订阅消费
                    isClose = true;
                    //关闭线程池，等待线程池积压消息处理
                    THREAD_POOL.shutdown();
                    //判断线程池是否关闭
                    while (!THREAD_POOL.isTerminated()) {
                        try {
                            //每200毫秒 判断线程池积压数量
                            getPoolBacklogSize();
                            TimeUnit.MILLISECONDS.sleep(200L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("订阅者关闭，线程池处理完毕");
                    System.exit(0);
                }
            });
        }
    }
}
