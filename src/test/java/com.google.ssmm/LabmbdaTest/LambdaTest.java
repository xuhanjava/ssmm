package com.google.ssmm.LabmbdaTest;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by xuhan
 * 2017/5/22
 * 下午5:26
 */
public class LambdaTest {
    public static void main(String[] args) {
       // Runnable r = () -> System.out.println("123");
        /*Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        };
        r.run();*/
        //Function
        /*Abc abc = (String a) ->{
            System.out.println(a);
            return 1;
        };
        System.out.println(abc.testPrint("a1"));*/
        System.out.println("content:"+test());
    }

    static Function test(){
        return t->t;
    }
}

@FunctionalInterface
interface Abc<A,B>{

    public int testPrint(String a);

    default Integer compose(Integer before) {
        return before;
    }
}


