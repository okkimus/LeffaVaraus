package com.vaadin;

import java.util.List;

public class KirjautumisKontrolli {

    public boolean signIn(List<Kayttaja> kayttajat, String username, String password) {
        for (Kayttaja k : kayttajat) {
            if (k.getKayttajatunnus().equals(username) && k.getSalasana().equals(password)) {
                KirjautunutKayttaja.set(username, k);
                return true;
            }
        }
        return false;
    }

    public void singOut() {
        KirjautunutKayttaja.set("",null);
    }

    public boolean isUserSignedIn() {
        return !(getKirjautunutKayttaja()==null);
    }

    public boolean isAdmin(String role) {
        if ("admin".equals(role)) {
            return getKayttajatunnus().equals("admin");
        }
        return false;
    }

    public String getKayttajatunnus() {
        return KirjautunutKayttaja.get();
    }

    public Kayttaja getKirjautunutKayttaja() {
        return KirjautunutKayttaja.getKirjautunutKayttaja();
    }
}
