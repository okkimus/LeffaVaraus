package com.vaadin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean admin;
    @NotNull
    private String nimi;
    @NotNull
    private String kayttajatunnus;
    @NotNull
    private String salasana;

    public Kayttaja() {
    }

    public Kayttaja(String nimi, String kayttajatunnus) {
        this.nimi = nimi;
        this.kayttajatunnus = kayttajatunnus;
    }

    public Kayttaja(String nimi, String kayttajatunnus, String salasana) {
        this.nimi = nimi;
        this.kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Kayttaja{" +
                "id=" + id +
                ", admin=" + admin +
                ", nimi='" + nimi + '\'' +
                ", kayttajatunnus='" + kayttajatunnus + '\'' +
                ", salasana='" + salasana + '\'' +
                '}';
    }
}
