package com.google.ssmm.CoreJavaTest;

/**
 * Created by xuhan on 17-4-4.
 */
public abstract class OverloadTest{
    public void show(){
        System.out.println("overloadTest");
    }

    public static void main(String[] args) {
        OverloadTest over = new OverLoadSubclass();
        over.show();
    }
}

class OverLoadSubclass extends  OverloadTest{
    @Override
    public void show(){
        System.out.println("subClass Test");
        super.show();
    }
}

