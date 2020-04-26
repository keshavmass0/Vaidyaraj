package com.example.vaidyaraj;

public class doctors_Model {

    String name;
    String gender ;

    public doctors_Model() {}
    public doctors_Model(String name, String gender) {
            this.name = name;
            this.gender = gender;
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



}
