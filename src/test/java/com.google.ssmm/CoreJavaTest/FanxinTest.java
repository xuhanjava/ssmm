package com.google.ssmm.CoreJavaTest;

import java.util.function.Function;

/**
 * Created by xuhan
 * 2017/5/23
 * 上午10:09
 */
public class FanxinTest<T,R> {

    public static void main(String[] args) {
        System.out.println(getTest);
        System.out.println(getTest.getClass());
        System.out.println(getTest.apply(1));
        System.out.println("--------------------");
        System.out.println(getTest1);
        System.out.println(getTest1.getClass());
        System.out.println(getTest1.apply(1));
    }

    public static Function<Integer,String> getTest = t ->{
        System.out.println(t);
        return "value:"+t.intValue();
    };

    public static Function<Integer,String> getTest1 = new Function<Integer, String>() {
        @Override
        public String apply(Integer integer) {
            System.out.println(integer);
            return "value1:"+integer.intValue();
        }
    };
}

class TestSA{
    <T extends Comparable> T compare(T t){
        return t;
    }
}
