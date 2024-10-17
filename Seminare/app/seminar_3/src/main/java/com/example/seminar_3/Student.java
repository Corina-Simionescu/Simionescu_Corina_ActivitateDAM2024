package com.example.seminar_3;

import java.io.Serializable;

public class Student implements Serializable {
    private String nume;
    private int grupa;
    private String facultate;
    private boolean esteIntegralist;

    public Student(String nume, int grupa, String facultate, boolean esteIntegralist) {
        this.nume = nume;
        this.grupa = grupa;
        this.facultate = facultate;
        this.esteIntegralist = esteIntegralist;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public boolean isEsteIntegralist() {
        return esteIntegralist;
    }

    public void setEsteIntegralist(boolean esteIntegralist) {
        this.esteIntegralist = esteIntegralist;
    }
}
