package com.example.diseasedetector.model;

import com.google.gson.annotations.SerializedName;

public class HeartAttackModel {

    private double attr_1;
    private double attr_2;
    private double attr_3;
    private double attr_4;

    private int predictioncol;

    public HeartAttackModel(double attr_1, double attr_2, double attr_3, double attr_4, int predictioncol) {
        this.attr_1 = attr_1;
        this.attr_2 = attr_2;
        this.attr_3 = attr_3;
        this.attr_4 = attr_4;
        this.predictioncol = predictioncol;
    }

    public double getAttr_1() {
        return attr_1;
    }

    public void setAttr_1(double attr_1) {
        this.attr_1 = attr_1;
    }

    public double getAttr_2() {
        return attr_2;
    }

    public void setAttr_2(double attr_2) {
        this.attr_2 = attr_2;
    }

    public double getAttr_3() {
        return attr_3;
    }

    public void setAttr_3(double attr_3) {
        this.attr_3 = attr_3;
    }

    public double getAttr_4() {
        return attr_4;
    }

    public void setAttr_4(double attr_4) {
        this.attr_4 = attr_4;
    }

    public int getPredictioncol() {
        return predictioncol;
    }

    public void setPredictioncol(int predictioncol) {
        this.predictioncol = predictioncol;
    }



}
