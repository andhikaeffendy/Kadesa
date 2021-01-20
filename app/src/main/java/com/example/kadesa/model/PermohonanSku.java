package com.example.kadesa.model;

public class PermohonanSku {

    String mFormulirKK, mTanggal, mIdSurat, mStatus;

    public PermohonanSku(String mFormulirKK, String mTanggal, String mIdSurat, String mStatus) {
        this.mFormulirKK = mFormulirKK;
        this.mTanggal = mTanggal;
        this.mIdSurat = mIdSurat;
        this.mStatus = mStatus;
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

    public String getmIdSurat() {
        return mIdSurat;
    }

    public void setmIdSurat(String mIdSurat) {
        this.mIdSurat = mIdSurat;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
