package com.example.kadesa.ui.pemerintah;

public class Pemerintah {
    private int id;
    private String name;

    public Pemerintah(int id, String name) {
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
