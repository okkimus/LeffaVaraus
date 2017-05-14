package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringComponent
public class OmatVarauksetContent extends HorizontalLayout {

    @Autowired
    VarausRepository repository;

    @Autowired
    NaytosRepository naytosRepository;

    @Autowired
    ElokuvaRepository elokuvaRepository;

    List<Varaus> varaukset;
    Binder<Varaus> binder = new Binder<>(Varaus.class);
    private long id;

    private Grid<Varaus> varausGrid;

    @PostConstruct
    void init() {
        update();
    }

    protected void update() {
        setVaraukset(repository.findAll());
    }

    private void setVaraukset(List<Varaus> varaukset) {
        removeAllComponents();

        this.id = kirjautuneenKayttajanID();

        if (this.id != 0) {
            this.varaukset = new ArrayList<>();
            // haetaan tietokannassa olleet varaukset ja lisätään ne this.varaukset
            // listaan, jos varaus kuuluu kirjautuneelle käyttäjälle
            for (int i = 0; i < varaukset.size(); i++) {
                if (varaukset.get(i).getVaraajanId() == id) {
                    this.varaukset.add(varaukset.get(i));
                }
            }
            luoNakyma();
        }
    }

    /**
     *  Palauttaa kirjautuneen käyttäjän ID:n
     *  @return käyttäjän ID tai 0 jos ei kirjautunutta käyttäjää
     */
    private long kirjautuneenKayttajanID() {
        try {
            KirjautumisKontrolli kirjautumisKontrolli = (KirjautumisKontrolli) getSession().getAttribute("kirjautumisKontrolli");
            return kirjautumisKontrolli.getKayttajaId();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     *  Luo käyttäjän varauksista listan, jossa näkyy varattujen näytösten tiedot sekä varatut paikat
     */
    private void luoNakyma() {
        if (this.varaukset.size() == 0) {
            addComponent(new Label("Sinulla ei ole vielä varattuja elokuvalippuja!"));
        } else {
            // naytoksienIdt listalla tarkistetaan, onko kyseisestä näytöksestä tehty jo "leffakortti" näkymään
            ArrayList<Integer> naytoksienIdt = new ArrayList<>();
            ArrayList<VerticalLayout> varauksienTiedot = new ArrayList<>();
            for (Varaus v : this.varaukset) {
                // jos näytöksestä ei ole vielä tehty "leffakorttia", luodaan se tässä
                if (!naytoksienIdt.contains(v.getNaytoksenId())) {
                    naytoksienIdt.add(v.getNaytoksenId());

                    Naytos n = new Naytos();
                    // etsitään oikea näytös tietokannasta
                    for (Naytos nn : naytosRepository.findAll()) {
                        if (nn.getId() == v.getNaytoksenId()) {
                            n = nn;
                        }
                    }

                    Elokuva elokuva = new Elokuva();
                    // etsitään näytökseen liittyvä elokuva tietokannasta
                    for (Elokuva e : this.elokuvaRepository.findAll()) {
                        if (v.getElokuvanNimi().equals(e.getNimi())) {
                            elokuva = e;
                        }
                    }

                    // luodaan näkymä varauksen näytöksen tiedoista
                    final VerticalLayout leffaKortti = new VerticalLayout();
                    String nimi = elokuva.getNimi();
                    String kuvanOsoite = elokuva.getKuvanOsoite();

                    Image kuva1 = new Image();
                    kuva1.setSource(new ThemeResource(kuvanOsoite));

                    final Label Nimi = new Label(nimi);
                    final Label sali = new Label(n.getSali());
                    final Label kellonAika = new Label(n.getKellonAika());
                    leffaKortti.addComponents(kuva1, Nimi, sali, kellonAika);
                    leffaKortti.addComponent(new Label("Rivi: " + v.getRivi() + " Paikka: " + v.getPaikka()));
                    varauksienTiedot.add(leffaKortti);

                } else {
                    // Kun naytoksestä on jo olemassa "leffakortti,
                    // etsitään naytoksienIdt listan avulla oikea komponentti, ja lisätään siihen paikkatieto
                    int indeksi = naytoksienIdt.indexOf(v.getNaytoksenId());
                    varauksienTiedot.get(indeksi).addComponent(new Label("Rivi: " + v.getRivi() + " Paikka: " + v.getPaikka()));
                }
            }
            // lisätään varaukset päänäkymään
            for (VerticalLayout v : varauksienTiedot) {
                addComponent(v);
            }
        }
    }
}
