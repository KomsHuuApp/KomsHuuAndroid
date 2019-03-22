package com.komshuu.komshuuandroidfrontend.models;

public class UserOrderList {
    private String flatNumber;
    private String order;


    public UserOrderList() {


    }

    public UserOrderList(String flatnumber, String str) {
        flatNumber = flatnumber;
        order = str;

    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatnumber) {
        flatNumber = flatnumber;

    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String str) {
        order = str;

    }


}
