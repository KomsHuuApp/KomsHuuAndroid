package com.komshuu.komshuuandroidfrontend.models;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String name;
    private String surname;
    private long role;
    private String relativeNumber;
    private String number;
    private String gender;
    private long apartmentId;
    private String password;
    private String username;
    private int flatNumber;

    public User(long id, String name, String surname, long role, String relativeNumber, String number,
                String gender, long apartmentId, String password, String username, int flatNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.relativeNumber = relativeNumber;
        this.number = number;
        this.gender = gender;
        this.apartmentId = apartmentId;
        this.password = password;
        this.username = username;
        this.flatNumber = flatNumber;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public String getRelativeNumber() {
        return relativeNumber;
    }

    public void setRelativeNumber(String relativeNumber) {
        this.relativeNumber = relativeNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }
}
