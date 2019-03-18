package com.komshuu.komshuuandroidfrontend.models;

public class Options {

    private long optionId;
    private String status;
    private long pollId;
    private int pollNumber;

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public int getPollNumber() {
        return pollNumber;
    }

    public void setPollNumber(int pollNumber) {
        this.pollNumber = pollNumber;
    }

}
