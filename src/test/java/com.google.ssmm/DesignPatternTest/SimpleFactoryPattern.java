package com.google.ssmm.DesignPatternTest;

/**
 * Created by xuhan on 17-3-30.
 */
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        Fruit fruit = Factory.createFruit("apple");
        if(fruit != null){
            fruit.eat();
        }
    }
}

class Factory{

    public static Fruit createFruit(String name){
        switch (name){
            case "apple":
                return new Apple();
            case "orange":
                return new Orange();
            default:
                return null;
        }
    }
}

interface Fruit{
    void eat();
}

class Apple implements Fruit{

    @Override public void eat() {
        System.out.println("eat apple");
    }
}

class Orange implements Fruit{

    @Override public void eat() {
        System.out.println("eat orange");
    }
}
