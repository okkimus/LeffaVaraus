package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;

public class Login extends VerticalLayout implements View {
    public Login() {
        setSizeFull();

        CssLayout csslayout = new CssLayout();
        csslayout.setWidth("100%");
        csslayout.addStyleName("flexwrap");
        addComponent(csslayout);

        Responsive.makeResponsive(csslayout);

        csslayout.addComponent(MyUI.getOtsikko());
        csslayout.addComponent(MyUI.getMenubar());
        csslayout.addComponent(new Label("Kirjaudu"));
        csslayout.addComponent(
                new VerticalLayout(
                    new TextField("Käyttäjänimi"),
                    new TextField("Salasana"),
                       new HorizontalLayout(
                           new Button("Kirjaudu"),
                           new Button("Rekisteröidy")
                       )
                )
        );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
