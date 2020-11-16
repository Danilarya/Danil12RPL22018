package com.workteam.danil12rpl22018.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SepedaModel implements Parcelable {
    private String UNIT_ID;
    private String UNIT_KODE;
    private String UNIT_MERK;
    private String UNIT_WARNA;
    private String UNIT_HARGA;
    private String UNIT_GAMBAR;

    public SepedaModel(Parcel in) {
        UNIT_ID = in.readString();
        UNIT_KODE = in.readString();
        UNIT_MERK = in.readString();
        UNIT_WARNA = in.readString();
        UNIT_HARGA = in.readString();
        UNIT_GAMBAR = in.readString();
    }

    public static final Creator<SepedaModel> CREATOR = new Creator<SepedaModel>() {
        @Override
        public SepedaModel createFromParcel(Parcel in) {
            return new SepedaModel(in);
        }

        @Override
        public SepedaModel[] newArray(int size) {
            return new SepedaModel[size];
        }
    };

    public SepedaModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUNIT_ID() {
        return UNIT_ID;
    }

    public void setUNIT_ID(String UNIT_ID) {
        this.UNIT_ID = UNIT_ID;
    }

    public String getUNIT_KODE() {
        return UNIT_KODE;
    }

    public void setUNIT_KODE(String UNIT_KODE) {
        this.UNIT_KODE = UNIT_KODE;
    }

    public String getUNIT_MERK() {
        return UNIT_MERK;
    }

    public void setUNIT_MERK(String UNIT_MERK) {
        this.UNIT_MERK = UNIT_MERK;
    }

    public String getUNIT_WARNA() {
        return UNIT_WARNA;
    }

    public void setUNIT_WARNA(String UNIT_WARNA) {
        this.UNIT_WARNA = UNIT_WARNA;
    }

    public String getUNIT_HARGA() {
        return UNIT_HARGA;
    }

    public void setUNIT_HARGA(String UNIT_HARGA) {
        this.UNIT_HARGA = UNIT_HARGA;
    }

    public String getUNIT_GAMBAR() {
        return UNIT_GAMBAR;
    }

    public void setUNIT_GAMBAR(String UNIT_GAMBAR) {
        this.UNIT_GAMBAR = UNIT_GAMBAR;
    }

    public static Creator<SepedaModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UNIT_ID);
        dest.writeString(UNIT_KODE);
        dest.writeString(UNIT_MERK);
        dest.writeString(UNIT_WARNA);
        dest.writeString(UNIT_HARGA);
        dest.writeString(UNIT_GAMBAR);
    }
}
