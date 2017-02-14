package com.google.ssmm.common;

/**
 * Created by xuhan on 17-1-18.
 */
public class DebugTest {
    public static void main(String[] args) {
        sayHi();
        System.out.println(123);
        XuhN.sayHis();
        System.out.printf("sds");
    }
    private static void sayHi(){
        System.out.println("hi!");
    }
}
class XuhN{
    public static void sayHis(){
        System.out.println("sayHis");
    }
}
