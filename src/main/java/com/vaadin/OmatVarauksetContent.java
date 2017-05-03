package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    int kayttajanId;

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
        long id;
        try {
            KirjautumisKontrolli kirjautumisKontrolli = (KirjautumisKontrolli) getSession().getAttribute("kirjautumisKontrolli");
            id = kirjautumisKontrolli.getKayttajaId();
        } catch (Exception e) {
            id = 0;
        }
        this.varaukset = new ArrayList<>();
        for (int i = 0; i < varaukset.size(); i++) {
            if (varaukset.get(i).getVaraajanId() == id) {
                this.varaukset.add(varaukset.get(i));
            }
        }
        if (this.varaukset.size() == 0) {
            addComponent(new Label("Sinulla ei ole vielä varattuja elokuvalippuja!"));
        } else {
            ArrayList<Integer> naytoksienIdt = new ArrayList<>();
            ArrayList<VerticalLayout> varauksienTiedot = new ArrayList<>();
            for (Varaus v : this.varaukset) {
                if (!naytoksienIdt.contains(v.getNaytoksenId())) {
                    naytoksienIdt.add(v.getNaytoksenId());

                    Naytos n = new Naytos();
                    for (Naytos nn : naytosRepository.findAll()) {
                        if (nn.getId() == v.getNaytoksenId()) {
                            n = nn;
                        }
                    }

                    Elokuva elokuva = new Elokuva();
                    for (Elokuva e : this.elokuvaRepository.findAll()) {
                        if (v.getElokuvanNimi().equals(e.getNimi())) {
                            elokuva = e;
                        }
                    }
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
                    int indeksi = naytoksienIdt.indexOf(v.getNaytoksenId());
                    varauksienTiedot.get(indeksi).addComponent(new Label("Rivi: " + v.getRivi() + " Paikka: " + v.getPaikka()));
                }
            }
            for (VerticalLayout vl : varauksienTiedot) {
                addComponent(vl);
            }

        }
    }

    void addVaraus(Varaus varaus) {
        repository.save(varaus);
        update();
    }


}
