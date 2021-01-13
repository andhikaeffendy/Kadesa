package com.example.kadesa.model;

public class PermohonanSku {

    String mNomorSurat, mNamaPerusahaan, mBidangUsaha;

    public PermohonanSku(String mNomorSurat, String mNamaPerusahaan, String mBidangUsaha) {
        this.mNomorSurat = mNomorSurat;
        this.mNamaPerusahaan = mNamaPerusahaan;
        this.mBidangUsaha = mBidangUsaha;
    }

    public String getmNomorSurat() {
        return mNomorSurat;
    }

    public void setmNomorSurat(String mNomorSurat) {
        this.mNomorSurat = mNomorSurat;
    }

    public String getmNamaPerusahaan() {
        return mNamaPerusahaan;
    }

    public void setmNamaPerusahaan(String mNamaPerusahaan) {
        this.mNamaPerusahaan = mNamaPerusahaan;
    }

    public String getmBidangUsaha() {
        return mBidangUsaha;
    }

    public void setmBidangUsaha(String mBidangUsaha) {
        this.mBidangUsaha = mBidangUsaha;
    }
}
