package com.google.ssmm.entity.lambda;

/**
 * Created by xuhan
 * 2017/9/18
 * 上午10:32
 */
public interface ParentInterface {
    void print();

    default void defaultMethod(){
        System.out.println("this jdk8 method");
    }
}
