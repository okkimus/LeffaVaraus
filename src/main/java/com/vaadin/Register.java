package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;

/**
 * Created by teppo on 19.3.2017.
 */
public class Register extends VerticalLayout implements View {
    public Register() {
        setSizeFull();

        CssLayout csslayout = new CssLayout();
        csslayout.setWidth("100%");
        csslayout.addStyleName("flexwrap");
        addComponent(csslayout);

        Responsive.makeResponsive(csslayout);

        csslayout.addComponent(MyUI.getOtsikko());
        csslayout.addComponent(MyUI.getMenubar());
        csslayout.addComponent(new Label("Rekisteröi uusi käyttäjä"));
        csslayout.addComponent(
                new VerticalLayout(
                        new TextField("Käyttäjänimi"),
                        new TextField("Salasana"),
                        new HorizontalLayout(
                                new Button("Kirjaudu"),
                                new Button("Peruuta")
                        )
                )
        );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
