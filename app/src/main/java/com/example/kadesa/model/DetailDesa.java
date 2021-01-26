package com.example.kadesa.model;

import com.google.gson.annotations.SerializedName;

public class DetailDesa {

    private String name;
    private String image;
    private String ambulance_call;
    private String description;
    @SerializedName("lat")
    private long mLat;
    @SerializedName("long")
    private long mLong;
    private String email;
    private String phone_number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmbulance_call() {
        return ambulance_call;
    }

    public void setAmbulance_call(String ambulance_call) {
        this.ambulance_call = ambulance_call;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getmLat() {
        return mLat;
    }

    public void setmLat(long mLat) {
        this.mLat = mLat;
    }

    public long getmLong() {
        return mLong;
    }

    public void setmLong(long mLong) {
        this.mLong = mLong;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
