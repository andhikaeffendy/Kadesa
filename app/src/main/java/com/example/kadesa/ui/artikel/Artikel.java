package com.example.kadesa.ui.artikel;

public class Artikel {

    private String mImgArtikel,mNamaArtikel;

    public Artikel(String mImgArtikel, String mNamaArtikel) {
        this.mImgArtikel = mImgArtikel;
        this.mNamaArtikel = mNamaArtikel;
    }

    public String getmImgArtikel() {
        return mImgArtikel;
    }

    public void setmImgArtikel(String mImgArtikel) {
        this.mImgArtikel = mImgArtikel;
    }

    public String getmNamaArtikel() {
        return mNamaArtikel;
    }

    public void setmNamaArtikel(String mNamaArtikel) {
        this.mNamaArtikel = mNamaArtikel;
    }
}
