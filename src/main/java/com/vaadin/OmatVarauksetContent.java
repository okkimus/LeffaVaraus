package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class OmatVarauksetContent extends HorizontalLayout {

    @Autowired
    VarausRepository repository;
    List<Varaus> varaukset;
    Binder<Varaus> binder = new Binder<>(Varaus.class);

    private Grid<Varaus> varausGrid;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setVaraukset(repository.findAll());
    }

    private void setVaraukset(List<Varaus> varaukset) {
        this.varaukset = varaukset;
        this.varausGrid = new Grid<>();
        removeAllComponents();

        varausGrid.setItems(varaukset);
        varausGrid.addColumn(Varaus::getElokuvanNimi).setCaption("Elokuva");
        varausGrid.addColumn(Varaus::getRivi).setCaption("Rivi");
        varausGrid.addColumn(Varaus::getPaikka).setCaption("Paikka");
        varausGrid.addColumn(Varaus::getId).setCaption("ID");

        addComponent(varausGrid);

    }

    void addVaraus(Varaus varaus) {
        repository.save(varaus);
        update();
    }


}
