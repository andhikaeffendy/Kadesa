package com.example.kadesa.model;

public class ArtikelTerbaru {

    private int id;
    private String mImgArtikelTerbaru, mJudulArtikel, mDeskripsiArtikelTerbaru;

    public ArtikelTerbaru(int id, String mImgArtikelTerbaru, String mJudulArtikel, String mDeskripsiArtikelTerbaru) {
        this.id = id;
        this.mImgArtikelTerbaru = mImgArtikelTerbaru;
        this.mJudulArtikel = mJudulArtikel;
        this.mDeskripsiArtikelTerbaru = mDeskripsiArtikelTerbaru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImgArtikelTerbaru() {
        return mImgArtikelTerbaru;
    }

    public void setmImgArtikelTerbaru(String mImgArtikelTerbaru) {
        this.mImgArtikelTerbaru = mImgArtikelTerbaru;
    }

    public String getmJudulArtikel() {
        return mJudulArtikel;
    }

    public void setmJudulArtikel(String mJudulArtikel) {
        this.mJudulArtikel = mJudulArtikel;
    }

    public String getmDeskripsiArtikelTerbaru() {
        return mDeskripsiArtikelTerbaru;
    }

    public void setmDeskripsiArtikelTerbaru(String mDeskripsiArtikelTerbaru) {
        this.mDeskripsiArtikelTerbaru = mDeskripsiArtikelTerbaru;
    }
}
