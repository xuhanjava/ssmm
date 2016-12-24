package com.google.ssmm.ThreadTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by xuhan on 16-12-21.
 */


/**
 * 阻塞队列 test(debug的时候是要把断点设置为Thread模式) 与一般的队列相比支持两个附加操作的 Queue，
 * 这两个操作是：获取元素时等待队列变为非空，以及存储元素时等待空间变得可用。
 *
        抛出异常	    特殊值	    阻塞	    超时
 插入	add(e)	    offer(e)	put(e)	offer(e, time, unit)
 移除	remove()	poll()	    take()	poll(time, unit)
 检查	element()	peek()	    不可用	不可用                  //获取但是并不移除元素
 */
public class ThreadTest2 {

    public static void main(String[] args) throws InterruptedException {
        //SynchronousQueue 在每个插入操作必须等待另一个线程的对应移除操作,反过来移除的时候也是这样.且只有在真正移除的时候该元素才会存在。
        //这个队列在Executors.newCachedThreadPool()中使用
        BlockingQueue<String> q = new SynchronousQueue<>();
        //new LinkedBlockingQueue<>(1)；
        //q.put("first");
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        new Thread(c1).start();
        new Thread(p).start();
    }
}


class Producer implements Runnable {
    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            queue.put(produce());
            System.out.println("生产一个放入到队列中");
        } catch (InterruptedException ex) {
            System.out.println("此时队列已满");
        }
    }

    private String produce() {
        return "1";
    }
}


class Consumer implements Runnable {
    private final BlockingQueue queue;

    Consumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            consume(queue.take());
            System.out.println("从队列中消费一个");
        } catch (InterruptedException ex) {
            System.out.println("此时队列中没有数据");
        }
    }

    private void consume(Object x) {
        System.out.println(x);
    }
}

