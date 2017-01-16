package com.google.ssmm.entity;

/**
 * Created by xuhan on 17-1-3.
 */
public class Apple {
    private Integer id;
    private String name;
    private Fruit fruit;

    @Override public String toString() {
        return "Apple{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", fruit=" + fruit +
            '}';
    }

    public Apple() {
    }

    public Apple(Integer id, String name, Fruit fruit) {
        this.id = id;
        this.name = name;
        this.fruit = fruit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}
