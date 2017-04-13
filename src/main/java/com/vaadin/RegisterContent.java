package com.vaadin;

import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
class RegisterContent extends HorizontalLayout {
    private TextField nimi = new TextField("Nimi");
    private TextField kayttajatunnus = new TextField("Käyttäjätunnus");
    private TextField salasana = new TextField("Salasana");

    @Autowired
    private KayttajaRepository repository;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setKayttajat();
    }

    private void setKayttajat() {
        removeAllComponents();
        addLomake();
        addShowRegisteredUsersLayout();
    }

    private void addLomake() {
        FormLayout lomake = new FormLayout();

        Button cancel = new Button("Peruuta", this::cancel);
        Button ok = new Button("OK", this::ok);
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        ok.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        nimi.setRequiredIndicatorVisible(true);
        kayttajatunnus.setRequiredIndicatorVisible(true);
        salasana.setRequiredIndicatorVisible(true);

        lomake.addComponent(new Label("Rekisteröi uusi käyttäjä"));
        lomake.addComponents(nimi, kayttajatunnus, salasana);
        lomake.addComponents(new HorizontalLayout(cancel, ok));
        addComponent(lomake);
    }

    private void ok(Button.ClickEvent event) {
        Kayttaja k = new Kayttaja(nimi.getValue(),
                kayttajatunnus.getValue(),
                salasana.getValue());
        addKayttaja(k);
        nimi.setValue("");
        kayttajatunnus.setValue("");
        salasana.setValue("");
    }

    private void cancel(Button.ClickEvent event) {
        nimi.setValue("");
        kayttajatunnus.setValue("");
        salasana.setValue("");
    }

    private void addShowRegisteredUsersLayout() { //TODO poista jossain välissä
        List<Kayttaja> kayttajat;
        kayttajat = repository.findAll();
        VerticalLayout users = new VerticalLayout();
        users.addComponent(new Label("Käyttäjät"));
        kayttajat.forEach(k -> users.addComponent(new Label(k.getNimi() + " "
                + k.getKayttajatunnus() + " "
                + k.getSalasana())));
        addComponent(users);
    }

    private void addKayttaja(Kayttaja kayttaja) {
        repository.save(kayttaja);
        update();
    }
}
