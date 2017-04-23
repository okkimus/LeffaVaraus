package com.vaadin;

import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.vaadin.Login.LOGINVIEW;

@SpringComponent
class RegisterContent extends VerticalLayout {
    private TextField nimi = new TextField("Koko nimi");
    private TextField kayttajatunnus = new TextField("Käyttäjätunnus");
    private PasswordField salasana = new PasswordField("Salasana");

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
    }

    private void addLomake() {
        Panel panel = new Panel();
        panel.setSizeUndefined();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.TOP_CENTER);

        FormLayout lomake = new FormLayout();

        Button ok = new Button("Rekisteröidy", this::ok);
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        ok.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        nimi.setRequiredIndicatorVisible(true);
        kayttajatunnus.setRequiredIndicatorVisible(true);
        salasana.setRequiredIndicatorVisible(true);

        lomake.addComponent(new Label("Rekisteröidy uudeksi käyttäjäksi"));
        lomake.addComponents(nimi, kayttajatunnus, salasana);
        lomake.addComponents(new HorizontalLayout(ok));
        lomake.setMargin(true);
        panel.setContent(lomake);
    }

    private void ok(Button.ClickEvent event) {
        Kayttaja k = new Kayttaja(nimi.getValue(),
                kayttajatunnus.getValue(),
                salasana.getValue());
        addKayttaja(k);
        nimi.setValue("");
        kayttajatunnus.setValue("");
        salasana.setValue("");
        getUI().getNavigator().navigateTo(LOGINVIEW);
    }

    private void addKayttaja(Kayttaja kayttaja) {
        repository.save(kayttaja);
        update();
    }
}
