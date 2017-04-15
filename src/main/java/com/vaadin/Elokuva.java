package com.vaadin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Elokuva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String nimi;
    @NotNull
    private String tyylilaji;

    private String kuvanOsoite;

    public Elokuva() {
    }

    public Elokuva(String nimi, String tyylilaji, String kuvanOsoite) {
        this.nimi = nimi;
        this.tyylilaji = tyylilaji;
        this.kuvanOsoite = kuvanOsoite;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setTyylilaji(String tyylilaji) {
        this.tyylilaji = tyylilaji;
    }

    public long getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTyylilaji() {
        return tyylilaji;
    }

    public String getKuvanOsoite() {
        if (kuvanOsoite!="") {
            return kuvanOsoite;
        } else {
            return "../../elokuvajulisteet/notfound.jpg";
        }
    }

    public void setKuvanOsoite(String kuvanOsoite) {
        this.kuvanOsoite = kuvanOsoite;
    }
}
