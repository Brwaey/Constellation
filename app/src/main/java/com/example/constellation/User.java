package com.example.constellation;


public class User {
    private int uid;
    private String username;
    private String password;
    private String age;
    private String constellation;

    public User() {

    }

    public User(String username, String password, String age, String constellation) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.constellation = constellation;
    }

    public User(int uid, String username, String password, String age, String constellation) {
        this.uid = uid;
        this.username = username;
//        this.password = password;
        this.age = age;
        this.constellation = constellation;
    }

    public int getUid() {
        return uid;
    }

    public void setusername(int uid) {
        this.uid = uid;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

        @Override
    public String toString() {
        return  "用户名："+username+"\n"+
                "性    别："+age+"\n"+
                "星    座："+constellation;
    }
}
