package com.vaadin;

import com.vaadin.server.Page;

import java.util.List;

public class KirjautumisKontrolli {

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

    public void singOut() {
        KirjautunutKayttaja.set("",null);
        Page.getCurrent().reload();
    }

    public boolean isUserSignedIn() {
        return !(getKirjautunutKayttaja()==null);
    }

    public boolean isAdmin() {
        return KirjautunutKayttaja.getKirjautunutKayttaja().isAdmin();
    }

    public String getKayttajatunnus() {
        return KirjautunutKayttaja.get();
    }

    public Kayttaja getKirjautunutKayttaja() {
        return KirjautunutKayttaja.getKirjautunutKayttaja();
    }
}
