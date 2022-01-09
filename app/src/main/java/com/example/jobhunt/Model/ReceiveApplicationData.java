package com.example.jobhunt.Model;

public class ReceiveApplicationData {

    String fullname;
    String email;
    String password;
    String phoneno;
    String date;
    String title;
    String description;
    String skill;
    String salary;
    String company;
    String city;
    String edu_des;
    String uid;
    String pushid;

    public ReceiveApplicationData() {
    }

    public ReceiveApplicationData(String fullname, String email, String password, String phoneno, String date, String title, String description, String skill, String salary, String company, String city, String edu_des, String uid, String pushid) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.date = date;
        this.title = title;
        this.description = description;
        this.skill = skill;
        this.salary = salary;
        this.company = company;
        this.city = city;
        this.edu_des = edu_des;
        this.uid = uid;
        this.pushid = pushid;
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEdu_des() {
        return edu_des;
    }

    public void setEdu_des(String edu_des) {
        this.edu_des = edu_des;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
