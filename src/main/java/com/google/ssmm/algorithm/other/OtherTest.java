package com.google.ssmm.algorithm.other;

public class OtherTest {
    public static void main(String[] args) {
        //EightQueen queen = new EightQueen();
        //queen.cal8queens(0);
        ZeroOneBag oneBag = new ZeroOneBag();
        oneBag.f(0,0);
        System.out.println(oneBag.maxW);


        ZeroOneBag oneBag1 = new ZeroOneBag();
        oneBag1.put(0,0);
        System.out.println(oneBag1.maxW);
        System.out.println("-------"+oneBag1.map);
    }
}
