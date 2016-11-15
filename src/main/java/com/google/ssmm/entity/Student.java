package com.google.ssmm.entity;

import java.io.Serializable;

/**
 * Created by xuhan on 16-11-16.
 */
public class Student implements Serializable{
    private Integer studentId;
    private String name;

    @Override public String toString() {
        return "Student{" +
            "studentId=" + studentId +
            ", name='" + name + '\'' +
            '}';
    }

    public Student(Integer studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public Student() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
