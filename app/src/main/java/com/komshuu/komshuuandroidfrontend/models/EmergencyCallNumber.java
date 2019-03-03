package com.komshuu.komshuuandroidfrontend.models;

public class EmergencyCallNumber {

    private long emergencyId;
    private String name;
    private String phoneNumber;
    private long apartmentId;
    private int imageID;

    public EmergencyCallNumber() {

    }

    public EmergencyCallNumber(long emergencyId, String name, String phoneNumber, long apartmentId, int imageID) {
        this.emergencyId = emergencyId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.apartmentId = apartmentId;
        this.imageID = imageID;
    }

    public long getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(long id) {
        this.emergencyId = id;
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

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
