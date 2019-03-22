package com.komshuu.komshuuandroidfrontend.models;

public class UserOrderList {
    private String flatNumber;
    private String order;
    private int orderId;
    private long apartmentId;



    public UserOrderList() {


    }

    public UserOrderList(String flatnumber, String str) {
        flatNumber = flatnumber;
        order = str;

    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        orderId = id;

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
