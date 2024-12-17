package com.example.test_2_practice_4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "travels")
public class Travel {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String departureCountry;
    private String travelDocument;
    private String arrivalCountry;
    private String price;

    public Travel(String departureCountry, String travelDocument, String arrivalCountry, String price) {
        this.departureCountry = departureCountry;
        this.travelDocument = travelDocument;
        this.arrivalCountry = arrivalCountry;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getTravelDocument() {
        return travelDocument;
    }

    public void setTravelDocument(String travelDocument) {
        this.travelDocument = travelDocument;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "departureCountry='" + departureCountry + '\'' +
                ", travelDocument='" + travelDocument + '\'' +
                ", arrivalCountry='" + arrivalCountry + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
