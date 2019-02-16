package com.komshuu.komshuuandroidfrontend.models;

public class Komshuu {
    private String name;
    private String phoneNumber;
    private String flatNumber;
    private int imageID;

    public Komshuu() {

    }

    public Komshuu(String name, String surname, String phoneNumber, String flatNumber, int imageID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.flatNumber = flatNumber;
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

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
