package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = OmatVaraukset.OMATVARAUKSET)
public class OmatVaraukset extends VerticalLayout implements View {
    public static final String OMATVARAUKSET = "Omat varaukset";



    @Autowired
    OmatVarauksetContent content = new OmatVarauksetContent();

    @PostConstruct
    void init() { addComponent(content);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }



}
