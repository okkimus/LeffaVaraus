package com.vaadin;


import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@DesignRoot
class ElokuvaYksittainenView extends VerticalLayout {
    private Label nimi;
    private Label tyylilaji;
    private Image kuva;


    public ElokuvaYksittainenView(Elokuva elokuva) {
        this.nimi = new Label(elokuva.getNimi());
        this.tyylilaji = new Label(elokuva.getTyylilaji());



    }

}
