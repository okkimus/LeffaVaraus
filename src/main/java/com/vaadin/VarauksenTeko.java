package com.vaadin;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = VarauksenTeko.VARAUKSENTEKO)
class VarauksenTeko extends VerticalLayout implements View {
    public static final String VARAUKSENTEKO= "Varaaminen";

    @Autowired
    VarausRepository repository;
    @Autowired
    VarauksenTekoContent varauksenTekoContent = new VarauksenTekoContent();

    @PostConstruct
    void init() {
        addComponent(varauksenTekoContent);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        int id = Integer.parseInt(event.getParameters());
        varauksenTekoContent.haeNaytosKannasta(id);
        init();
     }



}
