package com.komshuu.komshuuandroidfrontend.models;

public class UserOrder {
    private String text;
    private String count;



    public UserOrder(String text1, String count1) {
        text = text1;
        count = count1;
    }

    public String getText() {
        return text;
    }
    public String getCount() {

        return count;
    }

    public void changeCount(String str) {
        count = str;
    }




}
