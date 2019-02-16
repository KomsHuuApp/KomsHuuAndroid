package com.komshuu.komshuuandroidfrontend.models;

public class Apartment {

    private String name;
    private String address;
    private String flatNumber;
    private int imageID;

    public Apartment() {

    }

    public Apartment(String name, String address, String flatNumber, int imageID) {
        this.name = name;
        this.address = address;
        this.flatNumber = flatNumber;
        this.imageID = imageID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
