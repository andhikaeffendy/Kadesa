package com.example.kadesa.model;

public class LembagaDesa {

    private String imgAnggota, namaAnggota;

    public LembagaDesa(String imgAnggota, String namaAnggota) {
        this.imgAnggota = imgAnggota;
        this.namaAnggota = namaAnggota;
    }

    public String getImgAnggota() {
        return imgAnggota;
    }

    public void setImgAnggota(String imgAnggota) {
        this.imgAnggota = imgAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }
}
