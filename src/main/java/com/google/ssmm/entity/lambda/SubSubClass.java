package com.google.ssmm.entity.lambda;

/**
 * Created by xuhan
 * 2017/9/18
 * 上午10:35
 */
public class SubSubClass implements SubInterface {
    @Override
    public void print() {
        System.out.println("print ……");
    }

    @Override
    public void defaultMethod() {
        System.out.println("subSubClass defaultMethod");
    }

    public static void main(String[] args) {
        ParentInterface pInterface = new SubSubClass();
        pInterface.print();
        pInterface.defaultMethod();
    }
}
