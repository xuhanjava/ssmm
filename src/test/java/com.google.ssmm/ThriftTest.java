package com.google.ssmm;

/**
 * Created by xuhan
 * 2017/6/8
 * 下午8:34
 */
public class ThriftTest {
    public static void main(String[] args) {
        TestA t = new TestA();
        System.out.println(t.toString());
    }
}

class TestA{
    int a;
    int b;
    int c;

    @Override
    public String toString() {
        return "TestA{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
