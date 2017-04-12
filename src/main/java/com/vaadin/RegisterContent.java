package com.vaadin;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
public class RegisterContent extends HorizontalLayout implements KayttajaChangeListener {

    @Autowired
    KayttajaRepository repository;
    List<Kayttaja> kayttajat;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setKayttajat(repository.findAll());
    }

    private void setKayttajat(List<Kayttaja> kayttajat) {
        this.kayttajat = kayttajat;
        removeAllComponents();
        addComponent(new RegisterLomake(kayttajat, this));

        VerticalLayout users = new VerticalLayout();
        users.addComponent(new Label("Käyttäjät"));
        kayttajat.forEach(k -> users.addComponent(new Label(k.getNimi()+" "
                +k.getKayttajatunnus()+" "
                +k.getSalasana())));
        addComponent(users);
    }

    void addKayttaja(Kayttaja kayttaja) {
        repository.save(kayttaja);
        update();
    }

    @Override
    public void KayttajaChanged(Kayttaja kayttaja) {
        addKayttaja(kayttaja);
    }
}
