package com.komshuu.komshuuandroidfrontend.models;

public class Complaint {

    private String complaintDate;
    private String complaintDescription;
    private String complaintId;
    private String personId;

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    private String apartmentId;

    public String getComplaintID() {
        return complaintId;
    }

    public void setComplaintID(String complaintID) {
        this.complaintId = complaintID;
    }

    public String getPersonID() {
        return personId;
    }

    public void setPersonID(String personID) {
        this.personId = personID;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    private String apartmentID;

    public Complaint(){}

    public Complaint(String complaintDate, String complaintDescription,String personId,String complaintId,String apartmentId) {
        this.complaintDate = complaintDate;
        this.complaintDescription = complaintDescription;
        this.personId = personId;
        this.complaintId = complaintId;
        this.apartmentId = apartmentId;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

}
