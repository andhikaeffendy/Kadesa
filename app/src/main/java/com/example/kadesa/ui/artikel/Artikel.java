package com.example.kadesa.ui.artikel;

public class Artikel {

    private int mImgArtikel;
    private String mNamaArtikel;

    public Artikel(int mImgArtikel, String mNamaArtikel) {
        this.mImgArtikel = mImgArtikel;
        this.mNamaArtikel = mNamaArtikel;
    }

    public int getmImgArtikel() {
        return mImgArtikel;
    }

    public void setmImgArtikel(int mImgArtikel) {
        this.mImgArtikel = mImgArtikel;
    }

    public String getmNamaArtikel() {
        return mNamaArtikel;
    }

    public void setmNamaArtikel(String mNamaArtikel) {
        this.mNamaArtikel = mNamaArtikel;
    }
}
