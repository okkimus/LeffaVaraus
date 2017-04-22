package com.vaadin;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.List;

import static com.vaadin.ElokuvaKortti.ELOKUVAT;

@SpringView(name = Login.LOGINVIEW)
class Login extends CustomComponent implements View, Button.ClickListener {
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
        setSizeFull();
        FormLayout lomake = new FormLayout();

        kayttajatunnus = new TextField("Käyttätunnus");
        salasana = new PasswordField("Salasana");
        salasana.setValue("");

        Button kirjauduNappi = new Button("Kirjaudu", this);
        kirjauduNappi.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        kirjauduNappi.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        lomake.addComponent(new Label("Kirjaudu sisään"));
        lomake.addComponents(kayttajatunnus, salasana, kirjauduNappi);
        setCompositionRoot(lomake);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        kayttajatunnus.focus();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String username = kayttajatunnus.getValue();
        String password = this.salasana.getValue();

        boolean isValid = isValid(username, password);
        if (isValid) {
            for (Kayttaja k : kayttajat) {
                if (k.getKayttajatunnus().equals(username)) {
                    getSession().setAttribute("user", username);
                }
            }
            getUI().getNavigator().navigateTo(ELOKUVAT);
            Notification.show("Paina reload selaimessa",
                            "käyttäjätieto ei päivity muuten selaimeen :(",
                    Notification.Type.HUMANIZED_MESSAGE); // TODO poista kun korjattu
        } else {
            Notification.show("Antamasi salasana on väärä",
                    "Syötä salasana uudelleen",
                    Notification.Type.HUMANIZED_MESSAGE);
            this.salasana.setValue("");
            this.salasana.focus();
        }
    }

    private boolean isValid(String uname, String passwd) {
        for (Kayttaja k : kayttajat) {
            if (k.getKayttajatunnus().equals(uname) &&
                    k.getSalasana().equals(passwd)) {
                return true;
            }
        }
        return false;
    }
}
