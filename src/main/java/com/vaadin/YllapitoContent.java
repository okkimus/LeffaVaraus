package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class YllapitoContent extends HorizontalLayout {

    @Autowired
    ElokuvaRepository repository;
    List<Elokuva> elokuvat;
    Binder<Elokuva> binder = new Binder<>(Elokuva.class);

    private Grid<Elokuva> elokuvaGrid;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setElokuvat(repository.findAll());
    }

    private void setElokuvat(List<Elokuva> elokuvat) {
        this.elokuvat= elokuvat;
        this.elokuvaGrid = new Grid<Elokuva>();
        removeAllComponents();


        FormLayout form = new FormLayout();
        TextField nimi = new TextField("Nimi");
        TextField tyylilaji = new TextField("Tyylilaji");
        Elokuva elokuva = new Elokuva();
        Button cancel = new Button("Peruuta", event -> {
        });
        Button ok = new Button("OK", clickEvent -> {
        });
        ok.addStyleName( ValoTheme.BUTTON_FRIENDLY);

        form.addComponent(new Label("Lisää uusi elokuva"));
        form.addComponents(nimi, tyylilaji);
        form.addComponents(new HorizontalLayout(ok, cancel));

        ok.addClickListener(click -> {
            elokuva.setNimi(nimi.getValue());
            elokuva.setTyylilaji(tyylilaji.getValue());
            addElokuva(elokuva);
            nimi.setValue("");
            tyylilaji.setValue("");
            nimi.focus();
        });

        elokuvaGrid.setItems(elokuvat);

        elokuvaGrid.setSizeFull();
        elokuvaGrid.addColumn(Elokuva::getId).setCaption("Id");
        elokuvaGrid.addColumn(Elokuva::getNimi).setCaption("Nimi");
        elokuvaGrid.addColumn(Elokuva::getTyylilaji).setCaption("Tyylilaji");

        VerticalLayout elokuvatLista = new VerticalLayout();
        elokuvatLista.addComponents(new Label("Elokuvat"), elokuvaGrid);

        addComponent(form);
        addComponent(elokuvatLista);
    }

    void addElokuva(Elokuva elokuva) {
        repository.save(elokuva);
        update();
    }


}
