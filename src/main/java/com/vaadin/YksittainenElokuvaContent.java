package com.vaadin;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.vaadin.Login.LOGINVIEW;
import static com.vaadin.VarauksenTeko.VARAUKSENTEKO;

@SpringComponent
public class YksittainenElokuvaContent extends HorizontalLayout {
    @Autowired
    ElokuvaRepository repository;
    @Autowired
    NaytosRepository naytosRepository;

    Elokuva elokuva;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setElokuva();
    }

    private void setElokuva() {
        removeAllComponents();
        if (this.elokuva == null) {
            this.elokuva = new Elokuva("", "", "");
        }

        final HorizontalLayout leffaKortti = new HorizontalLayout();
        String nimi = this.elokuva.getNimi();
        String tyylilaji = this.elokuva.getTyylilaji();
        String kuvanOsoite = this.elokuva.getKuvanOsoite();

        Image kuva1 = new Image();
        kuva1.setSource(new ThemeResource(kuvanOsoite));

        final VerticalLayout leffatiedotright = new VerticalLayout();
        final VerticalLayout leffatiedotleft = new VerticalLayout();
        final Label Nimi = new Label(nimi);
        final Label Tyylilaji = new Label(tyylilaji);

        leffatiedotright.addComponents(Nimi, Tyylilaji);
        leffatiedotleft.addComponent(kuva1);
        leffaKortti.addComponents(leffatiedotleft, leffatiedotright);

        addComponent(leffaKortti);

        VerticalLayout naytokset = new VerticalLayout();

        int laskuri = 0;
        for (Naytos n : naytosRepository.findAll()) {
            if (Integer.parseInt(n.getElokuvanId())== this.elokuva.getId()) {
                HorizontalLayout naytos = new HorizontalLayout();
                KirjautumisKontrolli kirjautumisKontrolli = (KirjautumisKontrolli) getSession().getAttribute("kirjautumisKontrolli");

                Button varausNappi = new Button("Varaappa tästä lippus!");
                if (kirjautumisKontrolli.isUserSignedIn() == true) {
                    varausNappi.addClickListener(click ->
                            getUI().getNavigator().navigateTo(VARAUKSENTEKO + "/" + n.getId()));
                } else {
                    varausNappi.addClickListener(click ->
                            getUI().getNavigator().navigateTo(LOGINVIEW));
                    varausNappi.addClickListener(click ->
                            Notification.show("Kirjaudu sisään varataksesi lipun!"));
                }
                naytos.addComponents(new Label(
                                n.getKellonAika()),
                        new Label(n.getPaiva()),
                        new Label(n.getSali()),
                        varausNappi);

                naytokset.addComponent(naytos);
                laskuri++;
            }
        }
        if (laskuri == 0) {
            VerticalLayout vL = new VerticalLayout();
            vL.addComponent(new Label("Valitettavasti elokuvalla ei ole tulevia näytöksiä :("));
            addComponent(vL);
        } else {
            addComponent(naytokset);
        }

    }

    protected void haeElokuvaKannasta(int id) {
        for (Elokuva e : repository.findAll()) {
            if (e.getId() == id) {
                this.elokuva = e;
            }
        }
        setElokuva();
    }
}
