package com.ryfa.firebase.model;

public class Notification {

    String message, outPut, title;
    String txtBtn1 = "null", txtBtn2 = "null";
    String outPutBtn1 = "null", outPutBtn2 = "null";

    public String getTitle() {
        return title;
    }

    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getOutPut() {
        return outPut;
    }

    public Notification setOutPut(String outPut) {
        this.outPut = outPut;
        return this;
    }

    public String getTxtBtn1() {
        return txtBtn1;
    }

    public Notification setTxtBtn1(String txtBtn1) {
        this.txtBtn1 = txtBtn1;
        return this;
    }

    public String getTxtBtn2() {
        return txtBtn2;
    }

    public Notification setTxtBtn2(String txtBtn2) {
        this.txtBtn2 = txtBtn2;
        return this;
    }

    public String getOutPutBtn1() {
        return outPutBtn1;
    }

    public Notification setOutPutBtn1(String outPutBtn1) {
        this.outPutBtn1 = outPutBtn1;
        return this;
    }

    public String getOutPutBtn2() {
        return outPutBtn2;
    }

    public Notification setOutPutBtn2(String outPutBtn2) {
        this.outPutBtn2 = outPutBtn2;
        return this;
    }
}
