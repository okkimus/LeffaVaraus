package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = YllapitoElokuvat.YLLAPITOELOKUVAVIEW)
class YllapitoElokuvat extends VerticalLayout implements View {
    public static final String YLLAPITOELOKUVAVIEW = "Hallinoi elokuvia";

    @Autowired
    YllapitoElokuvatContent content = new YllapitoElokuvatContent();

    @PostConstruct
    void init() {
        addComponent(content);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
