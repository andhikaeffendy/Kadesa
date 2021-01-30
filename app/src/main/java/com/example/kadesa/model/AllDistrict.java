package com.example.kadesa.model;

import androidx.annotation.NonNull;

public class AllDistrict {

    private int id;
    private String name;

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public AllDistrict(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
