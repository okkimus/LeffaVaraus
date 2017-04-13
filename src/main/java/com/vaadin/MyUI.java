package com.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

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
@Theme("valo")
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {
    private VerticalLayout springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        setSizeFull();
        final VerticalLayout root = new VerticalLayout();
        root.addComponent(getOtsikko());
        root.setSizeFull();
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponents(getMenubar());
        navigationBar.setWidth("100%");
        root.addComponent(navigationBar);

        springViewDisplay = new VerticalLayout();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);
    }

    public static Label getOtsikko() {
        final Label Otsikko = new Label("Elokuvaraus");
        Otsikko.addStyleName("title");
        return Otsikko;
    }

    public MenuBar getMenubar() {
        MenuBar barmenu = new MenuBar();
        barmenu.setStyleName("topmenu");
        barmenu.setSizeFull();
        barmenu.addItem(ELOKUVAT,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                springViewDisplay.removeAllComponents();
                getUI().getNavigator().navigateTo(ELOKUVAT);
            }
        });
        barmenu.addItem(OMATVARAUKSET, new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem) {
                springViewDisplay.removeAllComponents();
                getUI().getNavigator().navigateTo(OMATVARAUKSET);
            }
        });
        barmenu.addItem(YLLAPITOVIEW, new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                springViewDisplay.removeAllComponents();
                getUI().getNavigator().navigateTo(YLLAPITOVIEW);
            }
        });
        barmenu.addItem(LOGINVIEW,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                springViewDisplay.removeAllComponents();
                getUI().getNavigator().navigateTo(LOGINVIEW);
            }
        });
        barmenu.addItem(REGISTERVIEW,new MenuBar.Command() {
            @Override public void menuSelected(MenuBar.MenuItem selectedItem){
                springViewDisplay.removeAllComponents();
                getUI().getNavigator().navigateTo(REGISTERVIEW);
            }
        });
        return barmenu;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.addComponent((Component) view);
    }
}
