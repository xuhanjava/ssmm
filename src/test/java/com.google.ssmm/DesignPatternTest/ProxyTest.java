package com.google.ssmm.DesignPatternTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xuhan on 17-4-4.
 */
public class ProxyTest {
    //Dynamic
    private static void publicLogic(Object proxy) {
        System.out.println("代理调用Target1");
        Target1 t1 = (Target1) proxy;
        t1.sayHi();

        System.out.println("代理调用Targe2");
        Target2 t2 = (Target2) proxy;
        t2.do1();
        t2.do2();

    }

    //Dynamic
    public static void logic1() {
        DynamicInvocation in = new DynamicInvocation();
        //第二个参数 我代理的类要实现的接口 第三个参数 解开标的类与拦截类之间的耦合
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
            new Class[] {Target1.class, Target2.class}, in);
        publicLogic(proxy);
    }

    //Dynamic
    public static void logic2(){
        DynamicInvocation in = new DynamicInvocation();
        //第二个参数 我代理的类要实现的接口 第三个参数 解开标的类与拦截类之间的耦合
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
            new Class[] {Target1.class}, in);
        publicLogic(proxy);
    }

    public static void main(String[] args) {
        logic1();
        //logic2();
    }
}


interface Target1 {
    void sayHi();
}


interface Target2 {
    void do1();

    void do2();
}


class TargetImplements implements Target1, Target2 {

    @Override public void sayHi() {
        System.out.println("hi!");
    }

    @Override public void do1() {
        System.out.println("do1");
    }

    @Override public void do2() {
        System.out.println("do2");
    }
}


class Intercept {
    public void before() {
        System.out.println("before!");
    }

    public void after() {
        System.out.println("after!");
    }
}


//Dynamic
class DynamicInvocation implements InvocationHandler {

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetImplements t = new TargetImplements();
        Intercept p = new Intercept();

        if (args != null && args.length != 1) {
            args[0] = "param value has changed";
        }
        p.before();
        Object o = method.invoke(t, args);
        System.out.println("args:" );
        p.after();
        return o;
    }
}

//Static
class StaticInvocation{
    public Object invoke(){
        TargetImplements t = new TargetImplements();
        Intercept p = new Intercept();
        p.before();
        t.do1();
        p.after();
        return null;
    }
}

//==========================================代理模式=========
interface Subject{
    void request();
}

class RealSubject implements Subject{

    @Override public void request() {
        System.out.println("真实的请求");
    }
}

class ProxySubject implements Subject{
    private RealSubject realSubject;
    @Override public void request() {
        if(realSubject == null){
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}
