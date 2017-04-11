package com.google.ssmm.entity;

import java.util.List;

/**
 * Created by xuhan on 17-1-3.
 */
public class Fruit {
    private Integer id;
    private String name;
    private String colour;
    private List<Apple> apples;

    public Fruit() {
    }

    public Fruit(Integer id, String name, String colour, List<Apple> apples) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.apples = apples;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public List<Apple> getApples() {
        return apples;
    }

    public void setApples(List<Apple> apples) {
        this.apples = apples;
    }

    @Override public String toString() {
        return "Fruit{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", colour='" + colour + '\'' +
            ", apples=" + apples +
            '}';
    }
}
