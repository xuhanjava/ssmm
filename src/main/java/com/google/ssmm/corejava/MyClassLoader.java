package com.google.ssmm.corejava;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
    public static String value = "test1";


     MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            if(name.endsWith("java.lang.Long")){
               return findClass(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        return null;
    }
}
