package com.google.ssmm.CoreJavaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by xuhan
 * 2017/5/23
 * 下午6:10
 */
public class FanxinTest2 {
    public static void main(String[] args) {
       /* ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Orange());

        for(Fruit fruit : fruits){
            System.out.println(fruit);
        }

        //这句话就是 说明是 Fruit或者是 Fruit的一个子类。
        List<? extends Fruit> fruits1 = fruits;

        //fruits1.add(new Fruit()); error
        //这个时候就是不能插入了，因为插入的时候不知道是哪种类型。可能等号前面的想表示是Fruit的一个子类，这时候你往里面插入的是其他子类。

        //而获取的函数就是能知道获取的是哪种类型
        Fruit fruit = fruits1.get(0);
        System.out.println(fruit.toString());*/

       Fr f = new Fr();
       f.andThen(r -> (People)r);
        System.out.println("123");
    }
}

class Fr<T extends R,R>{
    R apply(T t){
        return (R)t;
    }

    public <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
}

class Fruit extends People{
    @Override
    public String toString() {
        return "Fruit";
    }
}
class Apple extends Fruit{
    @Override
    public String toString() {
        return "Apple{}";
    }
}
class Orange extends Fruit{
    @Override
    public String toString() {
        return "Orange{}";
    }
}

class People{
    @Override
    public String toString() {
        return "People{}";
    }
}





