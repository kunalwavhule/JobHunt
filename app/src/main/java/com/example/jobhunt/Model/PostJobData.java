package com.example.jobhunt.Model;

public class PostJobData {
    String title;
    String description;
    String skill;
    String salary;
    String id;
    String date;
    String pushid;
    String company;
     String city;
    String status;
    String jobtypes;
    String schedule;

    public PostJobData() {


    }

    public PostJobData(String title, String description, String skill, String salary, String id, String date, String pushid, String company, String city, String status,  String jobtypes, String schedule) {
        this.title = title;
        this.description = description;
        this.skill = skill;
        this.salary = salary;
        this.id = id;
        this.date = date;
        this.pushid = pushid;
        this.company = company;
        this.city = city;
        this.status = status;
        this.jobtypes = jobtypes;
        this.schedule = schedule;
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

    public String getId() {
        return id;
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

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getJobtypes() {
        return jobtypes;
    }

    public void setJobtypes(String jobtypes) {
        this.jobtypes = jobtypes;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

}
