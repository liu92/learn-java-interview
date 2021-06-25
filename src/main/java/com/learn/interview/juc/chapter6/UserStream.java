package com.learn.interview.juc.chapter6;

/**
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/25 11:22 下午
 * @Author lin
 * @Version 1.0
 */


public class UserStream {
    private Integer id;
    private String userName;
    private double age;

    public  UserStream(){}
    public UserStream(Integer id, String userName, double age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
