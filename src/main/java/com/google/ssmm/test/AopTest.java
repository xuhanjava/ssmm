package com.google.ssmm.test;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class AopTest {
    public void test4(){
        /**
         * 三个参数
         * 1、ClassLoader
         * 方法需要动态生成一个类，这个类实现了A和B两个接口，然后创建这个类的对象
         * 需要生成一个类，这个类也需要加载到方法区中，所以我们需要一个ClassLoader来加载该类
         *
         * 2、Class[] interfaces
         * 我们需要代理对象实现的数组
         *
         * 3、InvocationHandler
         * 调用处理器
         *
         * 代理对象实现的所有接口中的方法，内容都是调用InvocationHandler的invoke()方法
         */
        ClassLoader classLoader = this.getClass().getClassLoader();
        //这里创建一个空实现的调用处理器。
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("你好！！！！"+method.getName());
                return "Hello";//这里改为返回"Hello"
            }
        };
        Object obj = Proxy.newProxyInstance(classLoader, new Class[]{A.class, B.class}, invocationHandler);
        //强转为A和B接口类型，说明生成的代理对象实现了A和B接口
        A a = (A) obj;
        B b = (B) obj;
        a.toString();//注意这里调用了toString()方法
        b.getClass();//注意这里调用了getClass()方法
        //这里在A接口中添加了一个方法public Object aaa(String s1, int i);
        Object hello = a.aaa("Hello", 100);
        System.out.println(obj.getClass());//这里看一下代理对象是什么
        System.out.println(hello);//这里看一下返回值是什么
    }
}
