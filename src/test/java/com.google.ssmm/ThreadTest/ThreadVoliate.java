package com.google.ssmm.ThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuhan on 16-12-30.
 */
public class ThreadVoliate {
    public static List<String> list = new ArrayList<>();
       // Collections.synchronizedList(new ArrayList<String>());

    public static void main(String[] args) throws InterruptedException {
        List<Thread> lists = new ArrayList<>();
        for(int i =1;i<=10000;i++){
            ThreadL threa = new ThreadL(""+i);
            lists.add(threa);
        }
        //threadA.interrupt();
        for(Thread thread:lists){
            thread.start();
        }
        Thread.sleep(3000);
        System.out.println("main is end:"+list.size());
    }
}
class ThreadL extends  Thread{
    String name;
    public ThreadL(String name){
        this.name = name;
    }

    @Override public void run() {
        synchronized (ThreadVoliate.class){
            ThreadVoliate.list.add(name);
            System.out.println(name+"size:"+ThreadVoliate.list.size());
        }
    }
}
