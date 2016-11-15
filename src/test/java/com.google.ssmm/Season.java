package com.google.ssmm;

/**
 * Created by xuhan on 16-11-18.
 */
public enum Season {
    SPRING("aaa"),SUMMER("bbb"),AUTUMN("ccc"),WINTER("ddd");

    private String value;
    private Season(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
