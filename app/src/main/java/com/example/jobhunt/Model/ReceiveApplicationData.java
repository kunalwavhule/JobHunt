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
    String EducationDec;
    String uid;
    String pushid;
    String profile;
    String CompanyName;
    String JobDescription;
    int Experience;



    public ReceiveApplicationData() {
    }

    public ReceiveApplicationData(String fullname, String email, String password, String phoneno, String date, String title, String description, String skill, String salary, String company, String city, String educationDec, String uid, String pushid, String profile, String companyName, String jobDescription, int experience) {
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
        EducationDec = educationDec;
        this.uid = uid;
        this.pushid = pushid;
        this.profile = profile;
        CompanyName = companyName;
        JobDescription = jobDescription;
        Experience = experience;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public String getEducationDec() {
        return EducationDec;
    }

    public void setEducationDec(String educationDec) {
        EducationDec = educationDec;
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
