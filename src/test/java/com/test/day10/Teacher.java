package com.test.day10;

import org.testng.annotations.DataProvider;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/3 22:02
 */

public class Teacher {
    private String name;
    private int age;
    private String sex;

    public Teacher() {
    }

    public Teacher(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
