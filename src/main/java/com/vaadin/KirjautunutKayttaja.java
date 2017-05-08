package com.vaadin;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

/**
 * Kirjautuneen käyttäjän tiedot
 */
public class KirjautunutKayttaja {
    public static Kayttaja kirjautunutKayttaja;
    public static final String Istunto_Attribuutti = KirjautunutKayttaja.class .getCanonicalName();

    private KirjautunutKayttaja() {
    }

    /**
     * Palauttaa kirjautuneen käyttäjän käyttäjätunnuksen
     * @return  kirjautuneen käyttäjän käyttäjätunnus
     */
    public static String get() {
        String currentUser = (String) getCurrentHttpSession().getAttribute(
                Istunto_Attribuutti);
        if (currentUser == null) {
            return "";
        } else {
            return currentUser;
        }
    }

    private static WrappedSession getCurrentHttpSession() {
        VaadinSession s = VaadinSession.getCurrent();
        if (s == null) {
            throw new IllegalStateException(
                    "Istuntoa ei löytynyt");
        }
        return s.getSession();
    }

    /**
     * Asettaa kirjautuneen käyttäjän käyttäjätunnuksen
     * @param currentUser   Kayttäjätunnus
     * @param k Käyttäjä
     */
    public static void set(String currentUser, Kayttaja k) {
        if (currentUser == null) {
            getCurrentHttpSession().removeAttribute(
                    Istunto_Attribuutti);
        } else {
            getCurrentHttpSession().setAttribute(
                    Istunto_Attribuutti, currentUser);
            kirjautunutKayttaja = k;
        }
    }

    /**
     * Palauttaa kirjautuneen käyttäjän esiintymän
     * @return  Kirjautuneen käyttäjän esiintymän
     */
    public static Kayttaja getKirjautunutKayttaja() {
        return kirjautunutKayttaja;
    }
}
