package com.example.seminar_11;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "caini")
public class Caine implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nume;
    private boolean esteAgresiv;
    private String rasa;
    private String dimensiune;
    private float nivelDeDragutenie;
    private boolean esteJucaus;

    public Caine(String nume, boolean esteAgresiv, String rasa, String dimensiune, float nivelDeDragutenie, boolean esteJucaus) {
        this.nume = nume;
        this.esteAgresiv = esteAgresiv;
        this.rasa = rasa;
        this.dimensiune = dimensiune;
        this.nivelDeDragutenie = nivelDeDragutenie;
        this.esteJucaus = esteJucaus;
    }

    protected Caine(Parcel in) {
        id = in.readLong();
        nume = in.readString();
        esteAgresiv = in.readByte() != 0;
        rasa = in.readString();
        dimensiune = in.readString();
        nivelDeDragutenie = in.readFloat();
        esteJucaus = in.readByte() != 0;
    }

    public static final Creator<Caine> CREATOR = new Creator<Caine>() {
        @Override
        public Caine createFromParcel(Parcel in) {
            return new Caine(in);
        }

        @Override
        public Caine[] newArray(int size) {
            return new Caine[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean isEsteAgresiv() {
        return esteAgresiv;
    }

    public void setEsteAgresiv(boolean esteAgresiv) {
        this.esteAgresiv = esteAgresiv;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(String dimensiune) {
        this.dimensiune = dimensiune;
    }

    public float getNivelDeDragutenie() {
        return nivelDeDragutenie;
    }

    public void setNivelDeDragutenie(float nivelDeDragutenie) {
        this.nivelDeDragutenie = nivelDeDragutenie;
    }

    public boolean isEsteJucaus() {
        return esteJucaus;
    }

    public void setEsteJucaus(boolean esteJucaus) {
        this.esteJucaus = esteJucaus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Caine{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", esteAgresiv=" + esteAgresiv +
                ", rasa='" + rasa + '\'' +
                ", dimensiune='" + dimensiune + '\'' +
                ", nivelDeDragutenie=" + nivelDeDragutenie +
                ", esteJucaus=" + esteJucaus +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nume);
        dest.writeByte((byte) (esteAgresiv ? 1 : 0));
        dest.writeString(rasa);
        dest.writeString(dimensiune);
        dest.writeFloat(nivelDeDragutenie);
        dest.writeByte((byte) (esteJucaus ? 1 : 0));
    }
}
