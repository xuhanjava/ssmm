package com.google.ssmm.ThreadTest;

/**
 * Created by xuhan on 17-3-29.
 */
class Example1 extends Thread {
    boolean stop = false;

    public static void main(String args[]) throws Exception {
       /* Example1 thread = new Example1();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");*/
        //System.exit(0);
        for(int i=1;i<=99999999999999999l;i++){

        }
    }

    public void run() {
        while (!stop) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();

        }
        System.out.println("Thread exiting under request...");
    }
}
