package com.example.jobhunt.Model;

public class Data {

    String id;
    String fullname;
    String email;
    String password;
    String phoneno;
    String date;
    int UserTypes;
    String applicantjobrole;
    String applicantcompany;
    String applicantjobdescription;
    String applicantexperience;
    String applicanteducationdesc;
    String companyaddress;
    String companytypes;
    String companydesc;
    String companyopeninghours;


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

    public Data(String id, String fullname, String email, String password, String phoneno, String date, int userTypes, String applicantjobrole, String applicantcompany, String applicantjobdescription, String applicantexperience, String applicanteducationdesc) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.date = date;
        UserTypes = userTypes;
        this.applicantjobrole = applicantjobrole;
        this.applicantcompany = applicantcompany;
        this.applicantjobdescription = applicantjobdescription;
        this.applicantexperience = applicantexperience;
        this.applicanteducationdesc = applicanteducationdesc;
    }

    public Data(String id, String fullname, String email, String password, String phoneno, String date, int userTypes, String applicantjobrole, String applicantcompany, String applicantjobdescription, String applicantexperience, String applicanteducationdesc, String companyaddress, String companytypes, String companydesc, String companyopeninghours) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.date = date;
        UserTypes = userTypes;
        this.applicantjobrole = applicantjobrole;
        this.applicantcompany = applicantcompany;
        this.applicantjobdescription = applicantjobdescription;
        this.applicantexperience = applicantexperience;
        this.applicanteducationdesc = applicanteducationdesc;
        this.companyaddress = companyaddress;
        this.companytypes = companytypes;
        this.companydesc = companydesc;
        this.companyopeninghours = companyopeninghours;
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

    public String getApplicantjobrole() {
        return applicantjobrole;
    }

    public void setApplicantjobrole(String applicantjobrole) {
        this.applicantjobrole = applicantjobrole;
    }

    public String getApplicantcompany() {
        return applicantcompany;
    }

    public void setApplicantcompany(String applicantcompany) {
        this.applicantcompany = applicantcompany;
    }

    public String getApplicantjobdescription() {
        return applicantjobdescription;
    }

    public void setApplicantjobdescription(String applicantjobdescription) {
        this.applicantjobdescription = applicantjobdescription;
    }

    public String getApplicantexperience() {
        return applicantexperience;
    }

    public void setApplicantexperience(String applicantexperience) {
        this.applicantexperience = applicantexperience;
    }

    public String getApplicanteducationdesc() {
        return applicanteducationdesc;
    }

    public void setApplicanteducationdesc(String applicanteducationdesc) {
        this.applicanteducationdesc = applicanteducationdesc;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getCompanytypes() {
        return companytypes;
    }

    public void setCompanytypes(String companytypes) {
        this.companytypes = companytypes;
    }

    public String getCompanydesc() {
        return companydesc;
    }

    public void setCompanydesc(String companydesc) {
        this.companydesc = companydesc;
    }

    public String getCompanyopeninghours() {
        return companyopeninghours;
    }

    public void setCompanyopeninghours(String companyopeninghours) {
        this.companyopeninghours = companyopeninghours;
    }
}
