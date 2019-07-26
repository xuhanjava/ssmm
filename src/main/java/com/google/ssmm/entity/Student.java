package com.google.ssmm.entity;

import lombok.Data;

@Data
public class Student {
    private int id;

    private String name;

    private Student student;
}
