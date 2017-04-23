package com.vaadin;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.List;

import static com.vaadin.ElokuvaKortti.ELOKUVAT;

@SpringView(name = Login.LOGINVIEW)
class Login extends VerticalLayout implements View, Button.ClickListener {
    static final String LOGINVIEW = "Kirjaudu";

    private final TextField kayttajatunnus;
    private final PasswordField salasana;

    @Autowired
    private KayttajaRepository repository;
    private List<Kayttaja> kayttajat;

    @PostConstruct
    void init() {
        this.kayttajat = repository.findAll();
    }

    public Login() {
        Panel panel = new Panel();
        panel.setSizeUndefined();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.TOP_CENTER);

        setSizeFull();
        FormLayout lomake = new FormLayout();

        kayttajatunnus = new TextField("Käyttäjätunnus");
        salasana = new PasswordField("Salasana");
        salasana.setValue("");

        Button kirjauduNappi = new Button("Kirjaudu", this);
        kirjauduNappi.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        kirjauduNappi.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        lomake.addComponent(new Label("Kirjaudu sisään"));
        lomake.addComponents(kayttajatunnus, salasana, kirjauduNappi);
        lomake.setMargin(true);
        panel.setContent(lomake);
    }

    private void naytaKirjautumisVinkki() {

        List<Kayttaja> kayttajat;
        kayttajat = repository.findAll();
        String users = "";
        for (Kayttaja k: kayttajat) {
            users += k.getKayttajatunnus() + " " + k.getSalasana() + " \n";
        }
        Notification notif = new Notification(
               "Testi tunnuksia",
                users,
                Notification.Type.HUMANIZED_MESSAGE);
        notif.setDelayMsec(10000); // 10 sek
        notif.setPosition(Position.BOTTOM_CENTER);
        notif.show(Page.getCurrent());
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String username = kayttajatunnus.getValue();
        String password = salasana.getValue();
        KirjautumisKontrolli kirjautumisKontrolli = (KirjautumisKontrolli) getSession().getAttribute("kirjautumisKontrolli");
        if (kirjautumisKontrolli.signIn(kayttajat, username, password)) {
            getUI().getNavigator().navigateTo(ELOKUVAT);
        } else {
            Notification.show("Antamasi salasana on väärä",
                    "Syötä salasana uudelleen",
                    Notification.Type.HUMANIZED_MESSAGE);
            this.salasana.setValue("");
            this.salasana.focus();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        kayttajatunnus.focus();
        naytaKirjautumisVinkki();
    }
}
