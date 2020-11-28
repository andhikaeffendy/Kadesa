package com.example.kadesa.model;

public class IndahDesaku {

    int id;
    String mImg, mJudul;

    public IndahDesaku(int id, String mImg, String mJudul) {
        this.id = id;
        this.mImg = mImg;
        this.mJudul = mJudul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public String getmJudul() {
        return mJudul;
    }

    public void setmJudul(String mJudul) {
        this.mJudul = mJudul;
    }
}
