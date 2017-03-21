package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    static Navigator navigator;
    protected static final String ELOKUVAT= "Elokuvat";
    protected static final String LOGINVIEW = "Kirjaudu";
    protected static final String REGISTERVIEW = "Rekisteröidy";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Elokuvavaraus");
        navigator = new Navigator(this, this);
        navigator.addView(ELOKUVAT, new Elokuvat());
        navigator.addView(LOGINVIEW, new Login());
        navigator.addView(REGISTERVIEW, new Register());
        navigator.navigateTo(ELOKUVAT);
    }

    public static Label getOtsikko() {
        final Label Otsikko = new Label("Elokuvaraus");
        Otsikko.addStyleName("title");
        return Otsikko;
    }

    public static MenuBar getMenubar() {
        MenuBar barmenu = new MenuBar();
        barmenu.setStyleName("topmenu");
        barmenu.setSizeFull();
        barmenu.addItem(ELOKUVAT,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                navigator.navigateTo(ELOKUVAT);
            }
        });
        barmenu.addItem("Omat Varaukset", null, null);
        barmenu.addItem("Ylläpito", null, null);
        barmenu.addItem(LOGINVIEW,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                navigator.navigateTo(LOGINVIEW);
            }
        });
        barmenu.addItem(REGISTERVIEW,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                navigator.navigateTo(REGISTERVIEW);
            }
        });
        return barmenu;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
