package com.google.ssmm.CoreJavaTest;

/**
 * Created by xuhan
 * 2017/7/19
 * 下午7:06
 */
public class FatherClassTest {
    public static void main(String[] args) {
        Father f = new SubClass();
        f.prif();
    }
}

class Father{
    protected final void prif(){
        System.out.println(this.getClass());
    }
}

class SubClass extends Father{

}