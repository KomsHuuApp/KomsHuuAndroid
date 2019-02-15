package com.komshuu.komshuuandroidfrontend.models;

public class EmergencyCallNumber {

    private String name;
    private String phoneNumber;
    private int imageID;

    public EmergencyCallNumber() {

    }

    public EmergencyCallNumber(String name, String phoneNumber, int imageID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageID = imageID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
