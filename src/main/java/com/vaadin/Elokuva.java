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

    public Elokuva() {
    }

    public Elokuva(String nimi, String tyylilaji) {
        this.nimi = nimi;
        this.tyylilaji = tyylilaji;
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

    @Override
    public String toString() {
        return "Elokuva{" +
                "id=" + id +
                ", nimi=" + nimi +
                ", tyylilaji='" + tyylilaji +
                '}';
    }
}
