package com.vaadin;

import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.vaadin.YksittainenElokuva.YKSITTAINENELOKUVAVIEW;

@SpringComponent
class ElokuvaKorttiContent extends HorizontalLayout {


    @Autowired
    private ElokuvaRepository elokuvaRepository;
    private List<Elokuva> elokuvat;

    @PostConstruct
    void init() {
        update();
    }

    public void update() {
        this.elokuvat = elokuvaRepository.findAll();
        setKayttajat();
    }

    private void setKayttajat() {
        removeAllComponents();
        addKortit();
    }

    private void addKortit() {
        CssLayout csslayout = new CssLayout();
        csslayout.setWidth("100%");
        csslayout.addStyleName("flexwrap");
        addComponent(csslayout);
        Responsive.makeResponsive(csslayout);

        for (Elokuva e : elokuvat) {
            final HorizontalLayout leffaKortti = new HorizontalLayout();
            String nimi = e.getNimi();
            String tyylilaji = e.getTyylilaji();
            String kuvanOsoite = e.getKuvanOsoite();

            Image kuva1 = new Image();
            kuva1.setSource(new ThemeResource(kuvanOsoite));

            final VerticalLayout leffatiedotright = new VerticalLayout();
            final VerticalLayout leffatiedotleft = new VerticalLayout();
            final Label Nimi = new Label(nimi);
            final Label Tyylilaji = new Label(tyylilaji);

            Button leffaNappi = new Button("Varaa lippu");
            leffaNappi.addClickListener(click ->
                    getUI().getNavigator().navigateTo(YKSITTAINENELOKUVAVIEW + "/" + e.getId()));
            leffatiedotright.addComponents(Nimi, Tyylilaji, leffaNappi);
            leffatiedotleft.addComponent(kuva1);
            leffaKortti.addComponents(leffatiedotleft, leffatiedotright);

            csslayout.addComponent(leffaKortti);
        }
        addComponent(csslayout);
    }
}
