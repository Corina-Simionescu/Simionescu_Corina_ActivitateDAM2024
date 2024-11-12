package com.example.test_1_practice_3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Dog implements Parcelable {
    private String name;
    private boolean isAggressive;
    private String breed;
    private String size;
    private double cutenessLevel;
    private boolean isPlayful;
    private Date dateOfBirth;

    public Dog(String name, boolean isAggressive, String breed, String size, double cutenessLevel, boolean isPlayful, Date dateOfBirth) {
        this.name = name;
        this.isAggressive = isAggressive;
        this.breed = breed;
        this.size = size;
        this.cutenessLevel = cutenessLevel;
        this.isPlayful = isPlayful;
        this.dateOfBirth = dateOfBirth;
    }

    protected Dog(Parcel in) {
        name = in.readString();
        isAggressive = in.readByte() != 0;
        breed = in.readString();
        size = in.readString();
        cutenessLevel = in.readDouble();
        isPlayful = in.readByte() != 0;
        dateOfBirth = (Date) in.readSerializable();
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAggressive() {
        return isAggressive;
    }

    public void setAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getCutenessLevel() {
        return cutenessLevel;
    }

    public void setCutenessLevel(double cutenessLevel) {
        this.cutenessLevel = cutenessLevel;
    }

    public boolean isPlayful() {
        return isPlayful;
    }

    public void setPlayful(boolean playful) {
        isPlayful = playful;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", isAggressive=" + isAggressive +
                ", breed='" + breed + '\'' +
                ", size='" + size + '\'' +
                ", cutenessLevel=" + cutenessLevel +
                ", isPlayful=" + isPlayful +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isAggressive ? 1 : 0));
        dest.writeString(breed);
        dest.writeString(size);
        dest.writeDouble(cutenessLevel);
        dest.writeByte((byte) (isPlayful ? 1 : 0));
        dest.writeSerializable(dateOfBirth);
    }
}
