package com.example.kadesa.model;

public class PermohonanSku {

    String mFormulirKK, mTanggal, mStatus;
    int mIdSurat;

    public PermohonanSku(String mFormulirKK, String mTanggal, String mStatus, int mIdSurat) {
        this.mFormulirKK = mFormulirKK;
        this.mTanggal = mTanggal;
        this.mStatus = mStatus;
        this.mIdSurat = mIdSurat;
    }

    public String getmFormulirKK() {
        return mFormulirKK;
    }

    public void setmFormulirKK(String mFormulirKK) {
        this.mFormulirKK = mFormulirKK;
    }

    public String getmTanggal() {
        return mTanggal;
    }

    public void setmTanggal(String mTanggal) {
        this.mTanggal = mTanggal;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public int getmIdSurat() {
        return mIdSurat;
    }

    public void setmIdSurat(int mIdSurat) {
        this.mIdSurat = mIdSurat;
    }
}
