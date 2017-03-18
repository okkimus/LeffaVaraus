package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        CssLayout csslayout = new CssLayout();
        csslayout.setWidth("100%");
        csslayout.addStyleName("flexwrap");
        layout.addComponent(csslayout);

        Responsive.makeResponsive(csslayout);

        final Label Otsikko = new Label("Elokuvaraus");
        Otsikko.addStyleName("title");
        csslayout.addComponent(Otsikko);

        final HorizontalLayout leffaKortti1 = new HorizontalLayout();
        final HorizontalLayout leffaKortti2 = new HorizontalLayout();
        final HorizontalLayout leffaKortti3 = new HorizontalLayout();

        Image kuva1 = new Image();
        kuva1.setSource(new ThemeResource("../../elokuvajulisteet/moonlight.jpg"));
        final VerticalLayout leffa1tiedotright = new VerticalLayout();
        final VerticalLayout leffa1tiedotleft= new VerticalLayout();
        final Label leffa1otsikko = new Label("Moonlight");
        Button leffa1nappi = new Button("Varaa lippu");
        leffa1tiedotright.addComponents(leffa1otsikko, leffa1nappi);
        leffa1tiedotleft.addComponent(kuva1);
        leffaKortti1.addComponents(leffa1tiedotleft, leffa1tiedotright);
        csslayout.addComponent(leffaKortti1);

        Image kuva2 = new Image();
        kuva2.setSource(new ThemeResource("../../elokuvajulisteet/vuosisadannaiset.jpg"));
        final VerticalLayout leffa2tiedotright = new VerticalLayout();
        final VerticalLayout leffa2tiedotleft= new VerticalLayout();
        final Label leffa2otsikko = new Label("Vuosisadan Naiset");
        Button leffa2nappi = new Button("Varaa lippu");
        leffa2tiedotright.addComponents(leffa2otsikko, leffa2nappi);
        leffa2tiedotleft.addComponent(kuva2);
        leffaKortti2.addComponents(leffa2tiedotleft, leffa2tiedotright);
        csslayout.addComponent(leffaKortti2);

        Image kuva3 = new Image();
        kuva3.setSource(new ThemeResource("../../elokuvajulisteet/legobatman.jpg"));
        final VerticalLayout leffa3tiedotright = new VerticalLayout();
        final VerticalLayout leffa3tiedotleft= new VerticalLayout();
        final Label leffa3otsikko = new Label("Lego Batman");
        Button leffa3nappi = new Button("Varaa lippu");
        leffa3tiedotright.addComponents(leffa3otsikko, leffa3nappi);
        leffa3tiedotleft.addComponent(kuva3);
        leffaKortti3.addComponents(leffa3tiedotleft, leffa3tiedotright);
        csslayout.addComponent(leffaKortti3);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
