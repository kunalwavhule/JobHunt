package com.example.jobhunt.Model;

public class RecruiterData {
    String id;
    String fullname;
    String email;
    String password;
    String gender;
    String phoneno;
    String date;

    public RecruiterData(String id, String fullname, String email, String password, String gender, String phoneno, String date) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneno = phoneno;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
