package com.google.ssmm.ThreadTest;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * 这就是并行执行的好处，如果串行执行的话就要6s,并行执行的话就少了三秒。这里需要注意的是依赖的调用就不能并行的调用了
 * Created by xuhan
 * 2017/7/22
 * 下午3:14
 */
public class BingxingTest {
    @Test
    public void testBing() throws ExecutionException, InterruptedException {
        System.out.println("begin time:" + new Date());
        Callable callable1 = new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000l);
                return "" + (new Date());
            }
        };
        FutureTask task = new FutureTask(callable1);

        Callable callable2 = new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000l);
                throw new RuntimeException("出错了");
            }
        };
        FutureTask task2 = new FutureTask(callable2);
        new Thread(task).start();
        new Thread(task2).start();
        try {
            task.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("超时了");
        }
        try {
            task2.get(2,TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("超时了");
        }
        System.out.println("main end time:"+new Date());
    }

    @Test
    public void testMap(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("w",null);
    }

    @Test
    public void testBingXing() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<?> submit = threadPool.submit(new FutureTask<Boolean>(
                () -> {
                    return true;
                }
        ));
        Future<?> submit1 = threadPool.submit(
                () -> {
                    return true;
                }
        );
        System.out.println(submit.get());
        System.out.println(submit1.get());
    }
}
