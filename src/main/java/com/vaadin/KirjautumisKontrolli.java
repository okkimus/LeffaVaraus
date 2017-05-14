package com.vaadin;

import com.vaadin.server.Page;

import java.util.List;

/**
 * Kirjautumistapahtumat
 */
public class KirjautumisKontrolli {

    /**
     * Kirjaudu sisään
     * @param kayttajat Rekisteröityneiden käyttäjien lista
     * @param username Kirjautuvan käyttäjän tarjoama käyttäjätunnus
     * @param password Kirjautuvat käyttäjän tarjoama salasana
     * @return  Onnistuiko kirjautuminen
     */
    public boolean signIn(List<Kayttaja> kayttajat, String username, String password) {
        for (Kayttaja k : kayttajat) {
            if (k.getKayttajatunnus().equals(username) && k.getSalasana().equals(password)) {
                KirjautunutKayttaja.set(username, k);
                Page.getCurrent().reload();
                return true;
            }
        }
        return false;
    }

    /**
     * Kirjaudu ulos
     */
    public void singOut() {
        KirjautunutKayttaja.set("",null);
        Page.getCurrent().reload();
    }

    /**
     * Onko kukaan kirjautunut sisään?
     * @return Onko kukaan kirjautunut sisään
     */
    public boolean isUserSignedIn() {
        return !(getKirjautunutKayttaja()==null);
    }

    /**
     * Onko käyttäjä admin käyttäjä?
     * @return Onko käyttäjä admin käyttäjä
     */
    public boolean isAdmin() {
        return KirjautunutKayttaja.getKirjautunutKayttaja().isAdmin();
    }

    /**
     * Palauttaa kirjautuneen käyttäjän käyttäjätunnuksen
     * @return Käyttäjätunnus
     */
    public String getKayttajatunnus() {
        return KirjautunutKayttaja.get();
    }

    /**
     * Palauttaa kirjautuneen käyttäjän id:n
     * @return käyttäjä id
     */
    public long getKayttajaId() {
        return KirjautunutKayttaja.getKirjautunutKayttaja().getId();
    }

    /**
     * Palauttaa kirjautuneen käyttäjän instanssin
     * @return  kirjautuneen käyttäjän instanssi
     */
    public Kayttaja getKirjautunutKayttaja() {
        return KirjautunutKayttaja.getKirjautunutKayttaja();
    }
}
