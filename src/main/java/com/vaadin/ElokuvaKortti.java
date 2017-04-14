package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ElokuvaKortti.ELOKUVAT)
class ElokuvaKortti extends VerticalLayout implements View {
    public static final String ELOKUVAT= "Elokuvat";

    @Autowired
    ElokuvaKorttiContent content = new ElokuvaKorttiContent();

    @PostConstruct
    void init() {
        addComponent(content);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}



//    public ElokuvaKortti() {
//        setSizeFull();
//
//        CssLayout csslayout = new CssLayout();
//        csslayout.setWidth("100%");
//        csslayout.addStyleName("flexwrap");
//        addComponent(csslayout);
//
//        Responsive.makeResponsive(csslayout);
//
//        final HorizontalLayout leffaKortti1 = new HorizontalLayout();
//        final HorizontalLayout leffaKortti2 = new HorizontalLayout();
//        final HorizontalLayout leffaKortti3 = new HorizontalLayout();
//
//        Image kuva1 = new Image();
//        kuva1.setSource(new ThemeResource("../../elokuvajulisteet/moonlight.jpg"));
//        final VerticalLayout leffa1tiedotright = new VerticalLayout();
//        final VerticalLayout leffa1tiedotleft= new VerticalLayout();
//        final Label leffa1otsikko = new Label("Moonlight");
//        Button leffa1nappi = new Button("Varaa lippu");
//        leffa1tiedotright.addComponents(leffa1otsikko, leffa1nappi);
//        leffa1tiedotleft.addComponent(kuva1);
//        leffaKortti1.addComponents(leffa1tiedotleft, leffa1tiedotright);
//        csslayout.addComponent(leffaKortti1);
//
//        Image kuva2 = new Image();
//        kuva2.setSource(new ThemeResource("../../elokuvajulisteet/vuosisadannaiset.jpg"));
//        final VerticalLayout leffa2tiedotright = new VerticalLayout();
//        final VerticalLayout leffa2tiedotleft= new VerticalLayout();
//        final Label leffa2otsikko = new Label("Vuosisadan Naiset");
//        Button leffa2nappi = new Button("Varaa lippu");
//        leffa2tiedotright.addComponents(leffa2otsikko, leffa2nappi);
//        leffa2tiedotleft.addComponent(kuva2);
//        leffaKortti2.addComponents(leffa2tiedotleft, leffa2tiedotright);
//        csslayout.addComponent(leffaKortti2);
//
//        Image kuva3 = new Image();
//        kuva3.setSource(new ThemeResource("../../elokuvajulisteet/legobatman.jpg"));
//        final VerticalLayout leffa3tiedotright = new VerticalLayout();
//        final VerticalLayout leffa3tiedotleft= new VerticalLayout();
//        final Label leffa3otsikko = new Label("Lego Batman");
//        Button leffa3nappi = new Button("Varaa lippu");
//        leffa3tiedotright.addComponents(leffa3otsikko, leffa3nappi);
//        leffa3tiedotleft.addComponent(kuva3);
//        leffaKortti3.addComponents(leffa3tiedotleft, leffa3tiedotright);
//        csslayout.addComponent(leffaKortti3);
//
//        addComponent(csslayout);
//    }
