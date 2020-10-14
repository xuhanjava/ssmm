package com.google.ssmm.hystrix;

public class Fruit {
    private Integer id;

    public Fruit(Integer id) {
        this.id = id;
    }

    public Fruit(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
