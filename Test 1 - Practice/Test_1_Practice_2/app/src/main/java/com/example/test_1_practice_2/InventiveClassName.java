package com.example.test_1_practice_2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class InventiveClassName implements Parcelable {
    private int intField;
    private float floatField;

    private double doubleField;
    private String stringField;

    public InventiveClassName(int intField, float floatField, double doubleField, String stringField) {
        this.intField = intField;
        this.floatField = floatField;
        this.doubleField = doubleField;
        this.stringField = stringField;
    }

    protected InventiveClassName(Parcel in) {
        intField = in.readInt();
        floatField = in.readFloat();
        doubleField = in.readDouble();
        stringField = in.readString();
    }

    public static final Creator<InventiveClassName> CREATOR = new Creator<InventiveClassName>() {
        @Override
        public InventiveClassName createFromParcel(Parcel in) {
            return new InventiveClassName(in);
        }

        @Override
        public InventiveClassName[] newArray(int size) {
            return new InventiveClassName[size];
        }
    };

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public float getFloatField() {
        return floatField;
    }

    public void setFloatField(float floatField) {
        this.floatField = floatField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(double doubleField) {
        this.doubleField = doubleField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    @Override
    public String toString() {
        return "InventiveClassName{" +
                "intField=" + intField +
                ", floatField=" + floatField +
                ", doubleField=" + doubleField +
                ", stringField='" + stringField + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(intField);
        dest.writeFloat(floatField);
        dest.writeDouble(doubleField);
        dest.writeString(stringField);
    }
}
