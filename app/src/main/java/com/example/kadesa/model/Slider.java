package com.example.kadesa.model;

public class Slider {
    int id;
    String name, created_at,image;

    public Slider(int id, String name, String created_at, String image) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.image = image;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
