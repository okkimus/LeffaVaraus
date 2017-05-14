package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class YllapitoNaytoksetContent extends HorizontalLayout {

    @Autowired
    NaytosRepository naytosRepository;
    List<Naytos> naytokset;
    Binder<Naytos> binder = new Binder<>(Naytos.class);

    private Grid<Naytos> naytosGrid;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setNaytokset(naytosRepository.findAll());
    }

    private void setNaytokset(List<Naytos> naytokset) {
        this.naytokset = naytokset;
        this.naytosGrid = new Grid<Naytos>();
        removeAllComponents();

        FormLayout form = new FormLayout();
        TextField elokuvanId = new TextField("Elokuvan Id");
        TextField sali = new TextField("Sali");
        TextField kellonAika = new TextField("Kellon aika");
        TextField paiva = new TextField("Päivä");
        TextField riveja = new TextField("Rivejä");
        TextField paikkojaRivilla = new TextField("Paikkoja rivillä");
        Naytos naytos = new Naytos();

        Button cancel = new Button("Peruuta", event -> {
        });
        Button ok = new Button("OK", clickEvent -> {
        });
        ok.addStyleName( ValoTheme.BUTTON_FRIENDLY);

        form.addComponent(new Label("Lisää uusi naytos"));
        form.addComponents(elokuvanId, sali, kellonAika, paiva, riveja, paikkojaRivilla);
        form.addComponents(new HorizontalLayout(ok, cancel));

        ok.addClickListener(click -> {
            naytos.setElokuvanId(elokuvanId.getValue());
            naytos.setSali(sali.getValue());
            naytos.setKellonAika(kellonAika.getValue());
            naytos.setPaiva(paiva.getValue());
            naytos.setRiveja(riveja.getValue());
            naytos.setPaikkojaRivilla(paikkojaRivilla.getValue());
            addNaytos(naytos);

            elokuvanId.setValue("");
            sali.setValue("");
            elokuvanId.focus();
        });

        addComponent(form);

        TextField elokuvanIdeditor = new TextField();
        TextField saliEditor = new TextField();
        TextField paikkojaEditor = new TextField();
        TextField rivejaEditor = new TextField();
        TextField paivaEditor = new TextField();
        TextField kelloEditor = new TextField();

        naytosGrid.setSelectionMode(Grid.SelectionMode.NONE);
        naytosGrid.setItems(naytokset);
        naytosGrid.setSizeFull();
        naytosGrid.addColumn(Naytos::getId).setCaption("Id");
        naytosGrid.addColumn(Naytos::getElokuvanId)
                .setEditorComponent(elokuvanIdeditor, Naytos::setElokuvanId)
                .setCaption("Elokuvan ID");
        naytosGrid.addColumn(Naytos::getSali).setCaption("Sali")
                .setEditorComponent(saliEditor, Naytos::setSali);
        naytosGrid.addColumn(Naytos::getRiveja).setCaption("Riveja")
                .setEditorComponent(rivejaEditor, Naytos::setRiveja);
        naytosGrid.addColumn(Naytos::getPaikkojaRivilla).setCaption("PaikkojaRivillä")
                .setEditorComponent(paikkojaEditor, Naytos::setPaikkojaRivilla);;
        naytosGrid.addColumn(Naytos::getPaiva).setCaption("Päivä")
                .setEditorComponent(paivaEditor, Naytos::setPaiva);
        naytosGrid.addColumn(Naytos::getKellonAika).setCaption("Kellonaika")
                .setEditorComponent(kelloEditor, Naytos::setKellonAika);

        naytosGrid.getEditor().setEnabled(true);

        VerticalLayout naytoksetLista = new VerticalLayout();
        naytosGrid.setSizeFull();
        naytoksetLista.addComponents(new Label("Näytokset (tuplaklikkaa muokataksesi näytöstä)"), naytosGrid);

        addComponent(naytoksetLista);

    }


    void addNaytos(Naytos naytos) {
        naytosRepository.save(naytos);
        update();
    }

}
