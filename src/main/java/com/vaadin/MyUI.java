package com.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.util.Objects;

import static com.vaadin.ElokuvaKortti.ELOKUVAT;
import static com.vaadin.Login.LOGINVIEW;
import static com.vaadin.Register.REGISTERVIEW;
import static com.vaadin.Yllapito.YLLAPITOVIEW;
import static com.vaadin.OmatVaraukset.OMATVARAUKSET;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("mytheme")
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {
    private VerticalLayout layout;
    private String username;
    private KirjautumisKontrolli kirjautumisKontrolli;

    @Override
    protected void init(VaadinRequest request) {
        Page.getCurrent().setTitle("LeffaVaraus");
        final VerticalLayout root = new VerticalLayout();
        kirjautumisKontrolli = new KirjautumisKontrolli();
        getSession().setAttribute("kirjautumisKontrolli", kirjautumisKontrolli);
        if (kirjautumisKontrolli.isUserSignedIn()) {
            username = kirjautumisKontrolli.getKirjautunutKayttaja().getKayttajatunnus();
        } else {
            username ="";
        }
        HorizontalLayout otsikko = new HorizontalLayout(getOtsikko());
        root.addComponent(otsikko);
        root.setComponentAlignment(otsikko, Alignment.MIDDLE_CENTER);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponents(getMenubar());
        navigationBar.setWidth("100%");
        root.addComponent(navigationBar);

        layout = new VerticalLayout();
        root.addComponent(layout);
        root.setExpandRatio(layout, 1.0f);
        getUI().getNavigator().navigateTo(ELOKUVAT);
    }

    private static Label getOtsikko() {
        final Label Otsikko = new Label("Elokuvan varaaminen");
        Otsikko.addStyleName("title");
        return Otsikko;
    }

    private MenuBar getMenubar() {
        MenuBar barmenu = new MenuBar();
        barmenu.setStyleName("topmenu");
        barmenu.setSizeFull();

        MenuBar.Command logout = new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                kirjautumisKontrolli.singOut();
            }
        };

        // Käyttäjä on kirjautunut sisään tavallisena käyttäjänä
        if (kirjautumisKontrolli.isUserSignedIn() && !kirjautumisKontrolli.isAdmin()) {
            String helloUser = kirjautumisKontrolli.getKirjautunutKayttaja().getNimi();
            barmenu.addItem(ELOKUVAT,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(ELOKUVAT));
            barmenu.addItem(OMATVARAUKSET,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(OMATVARAUKSET));
            barmenu.addItem(helloUser, VaadinIcons.USER, null);
            MenuBar.MenuItem ku = barmenu.addItem("Kirjaudu ulos", null, logout);
            ku.setStyleName("oikeaReuna");
        // Käyttäjä on admin
        } else if (kirjautumisKontrolli.isUserSignedIn() && kirjautumisKontrolli.isAdmin()) {
            String helloUser = kirjautumisKontrolli.getKirjautunutKayttaja().getNimi();
            barmenu.addItem(ELOKUVAT,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(ELOKUVAT));
            barmenu.addItem(OMATVARAUKSET,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(OMATVARAUKSET));
            barmenu.addItem(YLLAPITOVIEW,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(YLLAPITOVIEW));
            barmenu.addItem(helloUser, VaadinIcons.USER, null);
            MenuBar.MenuItem ku = barmenu.addItem("Kirjaudu ulos", null, logout);
            ku.setStyleName("oikeaReuna");
        // Käyttäjä ei ole kirjautunut
        } else {
            barmenu.addItem(ELOKUVAT,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(ELOKUVAT));
            MenuBar.MenuItem register = barmenu.addItem(REGISTERVIEW,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(REGISTERVIEW));
            MenuBar.MenuItem login = barmenu.addItem(LOGINVIEW,
                    (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(LOGINVIEW));
            login.setStyleName("oikeaReuna");
            register.setStyleName("oikeaReuna");
        }
        return barmenu;
    }

    @Override
    public void showView(View view) {
        layout.removeAllComponents();
        layout.addComponent((Component) view);
    }
}
