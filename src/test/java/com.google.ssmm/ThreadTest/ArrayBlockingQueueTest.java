package com.google.ssmm.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition await以原子的方式释放 和 锁
 * 允许发生“虚假唤醒
 * 使用两个Condition的原因：喜欢在单独的等待 set 中保存 put 线程和 take 线程，这样就可以在缓冲区中的项或空间变得可用时利用最佳规划，一次只通知一个线程
 *
 * 实际上用的是一个锁没有真正的并行执行
 * Created by xuhan on 17-4-26.
 */
public class ArrayBlockingQueueTest<E> {
    private ReentrantLock lock;
    private int putIndex;
    private int getIndex;

    //当前的容量
    private int count;

    private Object[] items;

    private Condition notFull;

    private Condition notEmpty;

    public ArrayBlockingQueueTest(int capacity) {
        lock = new ReentrantLock();
        items = new Object[capacity];
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    /**
     * 阻塞添加
     *
     * @param item
     */
    public void put(E item) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (items.length >= count) {
                notFull.await();
            }
            enqueue(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取
     * @param
     */
    public E take()throws InterruptedException{
        lock.lockInterruptibly();
        try{
            //防止假唤醒
            while(items.length == 0){
                notEmpty.await();
            }

            count--;
            E x = (E) items[putIndex];
            items[putIndex] = null;
            if(++putIndex == items.length){
                putIndex = 0;
            }
            notFull.signal();

            //还有一个对Itrs的处理
            return x;
        }finally {
            lock.unlock();
        }
    }

    /*public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }*/

    private void enqueue(Object item) {
        items[putIndex] = item;

        //没有考虑putIndex循环之后的情况
        if ((++putIndex) == items.length) {
            putIndex = 0;
        }
        notEmpty.signal();
        count++;
    }
}
