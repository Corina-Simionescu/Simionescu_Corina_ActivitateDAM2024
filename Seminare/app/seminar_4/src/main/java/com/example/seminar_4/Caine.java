package com.example.seminar_4;

import java.io.Serializable;

public class Caine implements Serializable {
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

    @Override
    public String toString() {
        return "Caine{" +
                "nume='" + nume + '\'' +
                ", esteAgresiv=" + esteAgresiv +
                ", rasa='" + rasa + '\'' +
                ", dimensiune='" + dimensiune + '\'' +
                ", nivelDeDragutenie=" + nivelDeDragutenie +
                ", esteJucaus=" + esteJucaus +
                '}';
    }
}
