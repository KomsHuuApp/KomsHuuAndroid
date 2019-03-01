package com.komshuu.komshuuandroidfrontend.models;

public class Complaint {

    private String complaintDate;
    private String complaintDescription;

    public Complaint(){}

    public Complaint(String complaintDate, String complaintDescription) {
        this.complaintDate = complaintDate;
        this.complaintDescription = complaintDescription;
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
