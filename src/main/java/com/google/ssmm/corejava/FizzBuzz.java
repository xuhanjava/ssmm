package com.google.ssmm.corejava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class FizzBuzz {
    Lock lock = new ReentrantLock();
    //fizz的条件队列
    Condition fizz = lock.newCondition();
    //buzz的条件队列
    Condition buzz = lock.newCondition();
    //fizzbuzz的条件队列
    Condition fizzbuzz = lock.newCondition();
    //number的条件队列
    Condition num = lock.newCondition();
    private int n;
    //循环的下标
    private volatile int index;

    public FizzBuzz(int n) {
        this.n = n;
        this.index = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        while (index<=n) {
            //如果index能被3整除且不能被5整除 则满足条件 打印fizz
            if (index%3==0 && index%5!=0) {
                printFizz.run();
                //index+1 并且唤醒所有线程进行下一个循环的判断
                index++;
                buzz.signalAll();
                fizzbuzz.signalAll();
                num.signalAll();
            }else{
                //不满足条件 进入条件队列等待被唤醒
                fizz.await();
            }
        }
        lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        while (index<=n) {
            //如果index能被5整除且不能被3整除 则满足条件 打印buzz
            if (index%5==0 && index%3!=0) {
                printBuzz.run();
                //index+1 并且唤醒所有线程进行下一个循环的判断
                index++;
                fizz.signalAll();
                fizzbuzz.signalAll();
                num.signalAll();
            }else{
                //不满足条件 进入条件队列等待被唤醒
                buzz.await();
            }
        }
        lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        while (index<=n) {
            //如果index能被3整除且也能被5整除 则满足条件 打印fizz
            if (index%3==0 && index%5==0) {
                printFizzBuzz.run();
                //index+1 并且唤醒所有线程进行下一个循环的判断
                index++;
                fizz.signalAll();
                buzz.signalAll();
                num.signalAll();
            }else{
                //不满足条件 进入条件队列等待被唤醒
                fizzbuzz.await();
            }
        }
        lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number( IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (index<=n) {
            //如果index既不能被3整除也不能被5整除 则满足条件 打印数字
            if (index%3!=0 && index%5!=0) {
                printNumber.accept( index );
                //index+1 并且唤醒所有线程进行下一个循环的判断
                index++;
                fizz.signalAll();
                buzz.signalAll();
                fizzbuzz.signalAll();
            }else{
                //不满足条件 进入条件队列等待被唤醒
                num.await();
            }
        }
        lock.unlock();
    }

    public static void main( String[] args ) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz( 10 );
        CountDownLatch latch = new CountDownLatch( 4 );
        Runnable fizz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable buzz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("buzz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable fizzbuzz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizzbuzz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable number = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number( new IntConsumer() {
                        @Override
                        public void accept( int value ) {
                            System.out.println(value);
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        new Thread( fizz ).start();
        new Thread( buzz ).start();
        new Thread( fizzbuzz ).start();
        new Thread( number ).start();
        latch.await();
        System.out.println("done...");
    }
}

