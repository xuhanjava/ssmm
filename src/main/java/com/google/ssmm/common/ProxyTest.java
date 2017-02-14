package com.google.ssmm.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试.甚至可以RMI远程调用。只需要在本地设置一个接口就行了,不需要在本地有接口的实现。
 * Created by xuhan on 17-1-18.
 */
public class ProxyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        InvocationHandler handler = new ProxyXuHan();
        //生成动态代理类对象
        Class<?> class1 =
            Proxy.getProxyClass(handler.getClass().getClassLoader(),new Class[]{com.google.ssmm.common.Subject.class} );
        Subject subject = (Subject) class1.newInstance();
        subject.hello("123");
        /*System.out.println(class1);
        System.out.println("isProxy:"+Proxy.isProxyClass(class1));
        //生成动态代理类实例
        Subject subject1 = (Subject) Proxy
            .newProxyInstance(Subject.class.getClassLoader(), Subject.class.getInterfaces(), handler);
        System.out.println(subject1.getClass());*/
    }
}

class ProxyXuHan implements InvocationHandler{

    private Object target;

    public ProxyXuHan(Object target){
        this.target = target;
    }

    public ProxyXuHan(){}
    /**
     * @param proxy 代理类实例 就是上面的subject1
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调入之前:"+method.toString()+"->thread:"+Thread.currentThread().getName()+"this:"+this.getClass());
      //  method.invoke(target,args);
        System.out.println("调入之后");
        return null;
    }
}

interface Subject
{
    void rent();

    void hello(String str);
}

class RealSubject implements Subject
{
    @Override
    public void rent()
    {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str)
    {
        System.out.println("hello: " + str);
    }
}
