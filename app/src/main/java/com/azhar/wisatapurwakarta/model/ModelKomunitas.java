package com.azhar.wisatapurwakarta.model;

import java.io.Serializable;

public class ModelKomunitas implements Serializable {

    private String idKomunitas, txtNamaKomunitas, txtOpenTime, GambarKomunitas, KategoriKomunitas;

    public String getIdKomunitas() {
        return idKomunitas;
    }

    public void setIdKomunitas(String idKomunitas) {
        this.idKomunitas = idKomunitas;
    }

    public String getTxtNamaKomunitas() {
        return txtNamaKomunitas;
    }

    public void setTxtNamaKomunitas(String txtNamaKomunitas) {
        this.txtNamaKomunitas = txtNamaKomunitas;
    }


    public String getTxtOpenTime() {
        return txtOpenTime;
    }

    public void setTxtOpenTime(String txtOpenTime) {
        this.txtOpenTime = txtOpenTime;
    }

    public String getGambarKomunitas() {
        return GambarKomunitas;
    }

    public void setGambarKomunitas(String gambarKomunitas) {
        this.GambarKomunitas = gambarKomunitas;
    }

    public String getKategoriKomunitas() {
        return KategoriKomunitas;
    }

    public void setKategoriKomunitas(String kategoriKomunitas) {
        this.KategoriKomunitas = kategoriKomunitas;
    }
}
