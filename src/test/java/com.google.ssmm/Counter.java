package com.google.ssmm;

public class Counter{
    long count = 0;

    public synchronized void add(long value){
        this.count = value;
        System.out.println(this.count);
    }
}
class CounterThread extends Thread{

    protected Counter counter = null;

    public CounterThread(Counter counter){
        this.counter = counter;

    }

    public void run() {
        for(int i=0; i<1000; i++){
            counter.add(i);
        }
    }
}
class Example {

    public static void main(String[] args){
        Counter counter = new Counter();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
    }
}
