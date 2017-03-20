package com.example.phong.android_studentprofilemanagement.Model;

import java.util.Date;

public class User {
    String id;
    String fullName;
    String code;
    Date DOB;
    String email;
    String address;
    String profilePicture;
    int role;

    public User() {
    }

    public User(String id, String fullName, String code, Date DOB, String email, String address, String profilePicture, int role) {
        this.id = id;
        this.fullName = fullName;
        this.code = code;
        this.DOB = DOB;
        this.email = email;
        this.address = address;
        this.profilePicture = profilePicture;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
