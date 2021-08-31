package com.chain.app_version1;


public class UserInfo {

    public UserInfo(){

    }

    private String userName;

    private String age;

    private int id;


    public int getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getUserName() {
        return userName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
