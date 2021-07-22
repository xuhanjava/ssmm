package com.google.ssmm.test;

public class Sub extends Base{
    String name ="Sub"; //注意的是这一行如果没有的话，打印的就是null了
    public void display(){
        System.out.println(name);
    }
}
