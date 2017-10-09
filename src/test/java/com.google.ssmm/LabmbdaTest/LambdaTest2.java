package com.google.ssmm.LabmbdaTest;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xuhan
 * 2017/5/24
 * 下午8:43
 */
public class LambdaTest2 {
    public static void main(String[] args) {
       /* List<String> list = new Apple1(Arrays.asList(1, 2, 3), new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return "value:" + integer;
            }
        });*/

        List<String> transform = Lists.transform(Arrays.asList(1, 2, 3), new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return "value:" + integer;
            }
        });
        System.out.println(123);
    }
}

class Fruit1<T> implements List<T>{
    public void getName(){
        System.out.println("Fruit");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}

class Apple1<F,T> extends Fruit1<T>{
    final List<F> fromList;
    final Function<? super F, ? extends T> function;

    Apple1(
            List<F> fromList, Function<? super F, ? extends T> function) {
        this.fromList = checkNotNull(fromList);
        this.function = checkNotNull(function);
    }
}
