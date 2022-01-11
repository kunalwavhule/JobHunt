package com.example.jobhunt.Model;

public class Data {

    String id;
    String fullname;
    String email;
    String password;
    String phoneno;
    String date;
    int UserTypes;

    public Data() {
    }

    public Data(String id, String fullname, String email, String password, String phoneno, String date, int userTypes) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.date = date;
        UserTypes = userTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserTypes() {
        return UserTypes;
    }

    public void setUserTypes(int userTypes) {
        UserTypes = userTypes;
    }
}
