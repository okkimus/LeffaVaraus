package com.vaadin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Naytos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String elokuvanId;
    @NotNull
    private String sali;
    @NotNull
    private String kellonAika;
    @NotNull
    private String paiva;
    @NotNull
    private String riveja;
    @NotNull
    private String paikkojaRivilla;

    public Naytos() {
    }

    public Naytos (String elokuvanId, String sali, String kellonAika, String paiva, String riveja, String paikkojaRivilla) {
        this.elokuvanId = elokuvanId;
        this.sali = sali;
        this.kellonAika = kellonAika;
        this.paiva = paiva;
        this.riveja = riveja;
        this.paikkojaRivilla = paikkojaRivilla;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setElokuvanId(String elokuvanId) {
        this.elokuvanId = elokuvanId;
    }

    public void setSali(String sali) {
        this.sali = sali;
    }

    public void setKellonAika(String kellonAika) {
        this.kellonAika = kellonAika;
    }

    public void setPaiva(String paiva) {
        this.paiva = paiva;
    }

    public void setRiveja(String riveja) {
        this.riveja = riveja;
    }

    public void setPaikkojaRivilla(String paikkojaRivilla) {
        this.paikkojaRivilla = paikkojaRivilla;
    }

    public long getId() {
        return id;
    }

    public String getElokuvanId() {
        return elokuvanId;
    }

    public String getSali() {
        return sali;
    }

    public String getKellonAika() {
        return kellonAika;
    }

    public String getPaiva() {
        return paiva;
    }

    public String getRiveja() {
        return riveja;
    }

    public String getPaikkojaRivilla() {
        return paikkojaRivilla;
    }
}
