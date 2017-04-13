package com.vaadin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Varaus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int varaajanId;
    @NotNull
    private String elokuvanNimi;
    @NotNull
    private int naytoksenId;
    @NotNull
    private int rivi;
    @NotNull
    private int paikka;

    public Varaus() {
    }

    public Varaus(int varaajanId, String elokuvanNimi, int naytoksenId, int rivi, int paikka) {
        this.varaajanId = varaajanId;
        this.elokuvanNimi = elokuvanNimi;
        this.naytoksenId = naytoksenId;
        this.rivi = rivi;
        this.paikka = paikka;
    }

    public void setRivi(int rivi) {
        this.rivi = rivi;
    }

    public void setPaikka(int paikka) {
        this.paikka = paikka;
    }

    public int getRivi() {
        return rivi;
    }

    public int getPaikka() {
        return paikka;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVaraajanId(int varaajanId) {
        this.varaajanId = varaajanId;
    }

    public void setElokuvanNimi(String elokuvanNimi) {
        this.elokuvanNimi = elokuvanNimi;
    }

    public void setNaytoksenId(int naytoksenId) {
        this.naytoksenId = naytoksenId;
    }

    public long getId() {
        return id;
    }

    public int getVaraajanId() {
        return varaajanId;
    }

    public String getElokuvanNimi() {
        return elokuvanNimi;
    }

    public int getNaytoksenId() {
        return naytoksenId;
    }
}
