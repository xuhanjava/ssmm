package com.google.ssmm.hystrix;

import java.io.Serializable;

public class Apple extends Fruit implements Serializable {
    private String appleName;


    public Apple(Integer id) {
        super(id);
    }

    public Apple(){
        super();
    }

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }


}
