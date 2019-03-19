package com.example.poc;

public class User {
    private String name;
    private String quan;
    private String uni;

    public User() {

    }

    public User(String name, String quan, String uni) {
        this.name = name;
        this.quan = quan;
        this.uni = uni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }
}
