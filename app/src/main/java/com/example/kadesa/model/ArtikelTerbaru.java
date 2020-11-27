package com.example.kadesa.model;

public class ArtikelTerbaru {

    private String mImgArtikelTerbaru, mJudulArtikel, mDeskripsiArtikelTerbaru;

    public ArtikelTerbaru(String mImgArtikelTerbaru, String mJudulArtikel, String mDeskripsiArtikelTerbaru) {
        this.mImgArtikelTerbaru = mImgArtikelTerbaru;
        this.mJudulArtikel = mJudulArtikel;
        this.mDeskripsiArtikelTerbaru = mDeskripsiArtikelTerbaru;
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
