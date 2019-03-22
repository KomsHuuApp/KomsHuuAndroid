package com.komshuu.komshuuandroidfrontend.models;

public class Dues {

    private long duesId;
    private String totalPayment;
    private long apartmentId;
    private int flatNumber;
    private String paymentDate;

    public Dues(){

    }

    public Dues(long duesId, String totalPayment, long apartmentId, int flatNumber, String paymentDate) {
        this.duesId = duesId;
        this.totalPayment = totalPayment;
        this.apartmentId = apartmentId;
        this.flatNumber = flatNumber;
        this.paymentDate = paymentDate;
    }

    public long getDuesId() {
        return duesId;
    }

    public void setDuesId(long duesId) {
        this.duesId = duesId;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
