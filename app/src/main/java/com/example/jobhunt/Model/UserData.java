package com.example.jobhunt.Model;

public class UserData {
    String id;
String fname;
String emailid;
String password;
String date;
int UserTypes;

    public UserData(String id, String fname, String emailid, String password, String date, int userTypes) {
        this.id = id;
        this.fname = fname;
        this.emailid = emailid;
        this.password = password;
        this.date = date;
        UserTypes = userTypes;
    }

    public String getId() {
        return id;
    }

    public int getUserTypes() {
        return UserTypes;
    }

    public void setUserTypes(int userTypes) {
        UserTypes = userTypes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
