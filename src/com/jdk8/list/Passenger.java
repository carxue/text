package com.jdk8.list;

public class Passenger {
    public Passenger() {
    }

    public Passenger(Boolean sex, String myCountry) {
        this.sex = sex;
        this.myCountry = myCountry;
    }

    private Boolean sex;
    private String myCountry;

    public String getMyCountry() {
        return myCountry;
    }

    public void setMyCountry(String myCountry) {
        this.myCountry = myCountry;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "sex=" + sex +
                ", myCountry='" + myCountry + '\'' +
                '}';
    }
}
