package com.google.ssmm.entity;

import java.util.Date;

/**
 * Created by xuhan on 16-12-5.
 */
public class Sort1 {
    private Date post_date;
    private String user;
    private String name;
    private Integer age;

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sort1(Date post_date, String user, String name, Integer age) {
        this.post_date = post_date;
        this.user = user;
        this.name = name;
        this.age = age;
    }

    public Sort1(){
    }
}

