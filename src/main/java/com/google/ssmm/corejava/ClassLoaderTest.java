package com.google.ssmm.corejava;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        MyClassLoader classLoader = new MyClassLoader(null,null);
//        Object o = classLoader.loadClass("java.lang.Long").newInstance();
//        System.out.println(o);

        System.out.println(MyClassLoader.value);
    }
}
