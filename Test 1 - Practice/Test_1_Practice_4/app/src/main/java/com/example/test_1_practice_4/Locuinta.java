package com.example.test_1_practice_4;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import java.util.Date;

public class Locuinta implements Parcelable {
    private String adresa;
    private boolean isDeLux;
    private String tip;
    private String confort;
    private float rating;
    private boolean isFrumoasa;
    private Date data;

    public Locuinta(String adresa, boolean isDeLux, String tip, String confort, float rating, boolean isFrumoasa, Date data) {
        this.adresa = adresa;
        this.isDeLux = isDeLux;
        this.tip = tip;
        this.confort = confort;
        this.rating = rating;
        this.isFrumoasa = isFrumoasa;
        this.data = data;
    }

    protected Locuinta(Parcel in) {
        adresa = in.readString();
        isDeLux = in.readByte() != 0;
        tip = in.readString();
        confort = in.readString();
        rating = in.readFloat();
        isFrumoasa = in.readByte() != 0;
        data = (Date) in.readSerializable();
    }

    public static final Creator<Locuinta> CREATOR = new Creator<Locuinta>() {
        @Override
        public Locuinta createFromParcel(Parcel in) {
            return new Locuinta(in);
        }

        @Override
        public Locuinta[] newArray(int size) {
            return new Locuinta[size];
        }
    };

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public boolean isDeLux() {
        return isDeLux;
    }

    public void setDeLux(boolean deLux) {
        isDeLux = deLux;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getConfort() {
        return confort;
    }

    public void setConfort(String confort) {
        this.confort = confort;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFrumoasa() {
        return isFrumoasa;
    }

    public void setFrumoasa(boolean frumoasa) {
        isFrumoasa = frumoasa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Locuinta{" +
                "adresa='" + adresa + '\'' +
                ", isDeLux=" + isDeLux +
                ", tip='" + tip + '\'' +
                ", confort='" + confort + '\'' +
                ", rating=" + rating +
                ", isFrumoasa=" + isFrumoasa +
                ", data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(adresa);
        dest.writeByte((byte) (isDeLux ? 1 : 0));
        dest.writeString(tip);
        dest.writeString(confort);
        dest.writeFloat(rating);
        dest.writeByte((byte) (isFrumoasa ? 1 : 0));
        dest.writeSerializable(data);
    }
}
