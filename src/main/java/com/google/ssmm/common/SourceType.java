package com.google.ssmm.common;

/**
 * Created by xuhan on 16-11-17.
 */
public enum SourceType {
    BAICHANGHUI("BAICHANGHUI"), HUIZHANG("HUIZHANG");

    private String name;

    SourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
