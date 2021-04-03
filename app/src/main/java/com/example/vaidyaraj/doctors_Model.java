package com.example.vaidyaraj;

public class doctors_Model {

    String name;
    String gender ;
    String Mobile_Number;
    String Visit_time;

    long patient_limit;
    public long getPatient_limit() {
        return (patient_limit);
    }


    public doctors_Model() {}
    public doctors_Model(String name, String gender, String Mobile_Number, String Visit_time) {
            this.name = name;
            this.gender = gender;
            this.Mobile_Number = Mobile_Number;
            this.Visit_time = Visit_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String Mobile_Number) {
        this.Mobile_Number = Mobile_Number;
    }
    public String getVisit_time() {
        return Visit_time;
    }

}