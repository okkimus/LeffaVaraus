package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = YksittainenElokuva.YKSITTAINENELOKUVAVIEW)
class YksittainenElokuva extends VerticalLayout implements View {
    public static final String YKSITTAINENELOKUVAVIEW= "Elokuva";

    @Autowired
    ElokuvaRepository repository;
    @Autowired
    YksittainenElokuvaContent content = new YksittainenElokuvaContent();


    @PostConstruct
    void init() {
        addComponent(content);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        int id = Integer.parseInt(event.getParameters());
        content.haeElokuvaKannasta(id);
        init();
    }
}
