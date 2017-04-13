package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

@SpringView(name = Login.LOGINVIEW)
public class Login extends VerticalLayout implements View {
    public static final String LOGINVIEW= "Kirjaudu";
    public Login() {
        setSizeFull();

        CssLayout csslayout = new CssLayout();
        csslayout.setWidth("100%");
        csslayout.addStyleName("flexwrap");
        addComponent(csslayout);

        Responsive.makeResponsive(csslayout);

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
