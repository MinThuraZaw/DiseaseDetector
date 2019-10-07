package com.example.diseasedetector.model;

public class PatientRecord {

    private String name;
    private String age;
    private String gender;
    private String weight;
    private String height;
    private String blood;
    private String happen;

    public PatientRecord(String name, String age, String gender, String weight, String height, String blood, String happen) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
        this.happen = happen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getHappen() {
        return happen;
    }

    public void setHappen(String happen) {
        this.happen = happen;
    }
}
