package com.google.ssmm.DesignPatternTest;

/**
 * Created by xuhan on 17-5-5.
 */
public class SingletonPatternTest {
    private static volatile SingletonPatternTest instance;

    private SingletonPatternTest(){}

    public static SingletonPatternTest getInstance(){
        if(instance == null){
            synchronized (SingletonPatternTest.class){
                if(instance == null){
                    instance = new SingletonPatternTest();
                }
            }
        }
        return instance;
    }
}
