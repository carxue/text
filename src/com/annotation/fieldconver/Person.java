package com.annotation.fieldconver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person<E> extends SupHuman {
    @FieldConvert(filed = "name")
    private String userName;
    @FieldConvert(filed = "psword")
    private String passWord;
    @FieldConvert(filed = "age")
    private Integer userAge;
    @FieldConvert(filed = "time")
    private Date createItme;
    private Boolean sex;
    private Double salary;
    @FieldConvert(filed = "elist")
    private List<E> list = new ArrayList<E>();

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Date getCreateItme() {
        return createItme;
    }

    public void setCreateItme(Date createItme) {
        this.createItme = createItme;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userAge=" + userAge +
                ", createItme=" + createItme +
                ", sex=" + sex +
                ", salary=" + salary +
                ", list=" + list +
                '}';
    }
}
