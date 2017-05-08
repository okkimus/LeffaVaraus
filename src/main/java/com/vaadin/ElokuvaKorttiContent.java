package com.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
class ElokuvaKorttiContent extends HorizontalLayout {

    @Autowired
    private ElokuvaRepository repository;
    private List<Elokuva> elokuvat;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        this.elokuvat = repository.findAll();
        setKayttajat();
    }

    private void setKayttajat() {
        removeAllComponents();
        addKortit();
    }

    /**
     * Elokuvanäkymän käyttöliittymä
     */
    private void addKortit() {
        CssLayout csslayout = new CssLayout();
        csslayout.setSizeFull();

        for (Elokuva e : elokuvat) {
            final HorizontalLayout leffaKortti = new HorizontalLayout();
            String nimi = e.getNimi();
            String tyylilaji = e.getTyylilaji();
            String kuvanOsoite = e.getKuvanOsoite();

            Image kuva = new Image();
            kuva.setSource(new ThemeResource(kuvanOsoite));

            final VerticalLayout leffatiedotright = new VerticalLayout();
            final VerticalLayout leffatiedotleft = new VerticalLayout();
            final Label Nimi = new Label(nimi);
            final Label Tyylilaji = new Label(tyylilaji);

            Button leffaNappi = new Button("Varaa lippu");

            leffatiedotright.addComponents(Nimi, Tyylilaji, leffaNappi);
            leffatiedotleft.addComponent(kuva);
            leffaKortti.addComponents(leffatiedotleft, leffatiedotright);

            csslayout.addComponent(leffaKortti);
        }
        Panel panel = new Panel();
        panel.setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.TOP_CENTER);
        panel.setContent(csslayout);
    }
}
