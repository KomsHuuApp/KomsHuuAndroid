package com.komshuu.komshuuandroidfrontend.models;

public class Announcement {

    private String announcementDate;
    private String announcementDescription;
    private int imageID;

    public Announcement() {
    }

    public Announcement(int imageID, String announcementDate, String announcementDescription) {
        this.imageID = imageID;
        this.announcementDate = announcementDate;
        this.announcementDescription = announcementDescription;
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

    /*public ArrayList<Announcement> getData() {
        ArrayList<Announcement> announcementList = new ArrayList<Announcement>();
        return announcementList;
    }*/
}
