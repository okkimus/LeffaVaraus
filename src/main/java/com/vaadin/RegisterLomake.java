package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import static com.vaadin.ElokuvaKortti.ELOKUVAT;
import static com.vaadin.Register.REGISTERVIEW;

public class RegisterLomake extends FormLayout {
    private TextField nimi = new TextField("Nimi");
    private TextField kayttajatunnus = new TextField("Käyttäjätunnus");
    private TextField salasana = new TextField("Salasana");
    Binder<Kayttaja> binder = new Binder<>(Kayttaja.class);
    Kayttaja kayttaja = new Kayttaja();

    public RegisterLomake(List<Kayttaja> kayttajat, KayttajaChangeListener changeListener) {

        Button cancel = new Button("peruuta", event -> {
        });
        Button ok = new Button("OK", event -> {
        });
        ok.addStyleName( ValoTheme.BUTTON_FRIENDLY);

        addComponent(new Label("Rekisteröi uusi käyttäjä"));
        nimi.setRequiredIndicatorVisible(true);
        kayttajatunnus.setRequiredIndicatorVisible(true);
        salasana.setRequiredIndicatorVisible(true);
        addComponents(nimi,kayttajatunnus,salasana);
        addComponents(new HorizontalLayout(cancel, ok));

        binder.bindInstanceFields(this);
        binder.setBean(kayttaja);
        binder.addValueChangeListener(event -> changeListener.KayttajaChanged(kayttaja));
    }
}
