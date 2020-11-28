package com.example.kadesa.ui.artikel;

public class Artikel {

    private String mImgArtikel,mNamaArtikel;
    private int id;

    public Artikel(String mImgArtikel, String mNamaArtikel, int id) {
        this.mImgArtikel = mImgArtikel;
        this.mNamaArtikel = mNamaArtikel;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
