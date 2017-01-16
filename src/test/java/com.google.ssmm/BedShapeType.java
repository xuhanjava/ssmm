package com.google.ssmm;

/**
 * Created by xuhan on 16-12-27.
 */
public enum BedShapeType {
    SINGLE_BED("SINGLE_BED"), DOUBLE_BED("DOUBLE_BED"), BUNK_BED("BUNK_BED"), TATAMI("TATAMI"), MATTRESS(
        "MATTRESS"), TONGPU("TONGPU"), TENT("TENT"), OTHER("OTHER");
    private String value;

    private BedShapeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
