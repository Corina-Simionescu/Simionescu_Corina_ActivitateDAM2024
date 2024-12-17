package com.example.test_2_practice_3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "oameni")
public class Om {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nume;
    private int varsta;

    public Om(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Om{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
