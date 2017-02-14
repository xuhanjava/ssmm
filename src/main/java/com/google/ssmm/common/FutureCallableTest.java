package com.google.ssmm.common;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by xuhan on 17-2-4.
 */
public class FutureCallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override public Integer call() throws Exception {
                System.out.println(new Date());
                Thread.sleep(3000);
                System.out.println(new Date());
                return 1;
            }
        });
        System.out.println("这个时候我开始做些别的工作"+new Date());
        Thread.sleep(2000);
        System.out.println("别的工作做完了"+new Date());
        System.out.println("future output:"+future.get());
    }
}


/**
 *  FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，
 *  又可以作为Future得到Callable的返回值，那么这个组合的使用有什么好处呢？假设有一个很耗时的返回值需要计算，
 *  并且这个返回值不是立刻需要的话，那么就可以使用这个组合，用另一个线程去计算返回值，
 *  而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过Future得到
 */
class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
