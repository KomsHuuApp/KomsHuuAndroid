package com.komshuu.komshuuandroidfrontend.models;

public class Announcement {

    private long announcementId;
    private String announcementDate;
    private String announcementDescription;
    private long apartmentId;
    private int announcementImportance;
    private long announcerId;
    private int imageID;

    public Announcement() {
    }

    public Announcement(int imageID, String announcementDate, String announcementDescription) {
        this.imageID = imageID;
        this.announcementDate = announcementDate;
        this.announcementDescription = announcementDescription;
    }

    public long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(long announcementId) {
        this.announcementId = announcementId;
    }

    public int getAnnouncementImportance() {
        return announcementImportance;
    }

    public void setAnnouncementImportance(int announcementImportance) {
        this.announcementImportance = announcementImportance;
    }

    public long getAnnouncerId() {
        return announcerId;
    }

    public void setAnnouncerId(long announcerId) {
        this.announcerId = announcerId;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(String announcementDate) {
        this.announcementDate = announcementDate;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcementDescription) {
        this.announcementDescription = announcementDescription;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    /*public ArrayList<Announcement> getData() {
        ArrayList<Announcement> announcementList = new ArrayList<Announcement>();
        return announcementList;
    }*/
}
