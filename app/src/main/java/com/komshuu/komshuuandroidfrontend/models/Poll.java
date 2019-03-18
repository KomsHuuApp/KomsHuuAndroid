package com.komshuu.komshuuandroidfrontend.models;
import java.util.List;

public class Poll {
    private long pollID;
    private String pollName;
    private long apartmentID;
    private String pollDate;
    private boolean pollState;

    public long getPollID() {
        return pollID;
    }

    public void setPollID(long pollID) {
        this.pollID = pollID;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public long getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(long apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getPollDate() {
        return pollDate;
    }

    public void setPollDate(String pollDate) {
        this.pollDate = pollDate;
    }

    public boolean isPollState() {
        return pollState;
    }

    public void setPollState(boolean pollState) {
        this.pollState = pollState;
    }

}

