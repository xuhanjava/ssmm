package com.google.ssmm;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuhan
 * 2017/9/13
 * 下午5:11
 */
public class SendClueMultiThread implements Runnable {
    CountDownLatch countDownLatch;
    public SendClueMultiThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----"+1);
        if(countDownLatch != null){
            countDownLatch.countDown();
        }
    }
}
