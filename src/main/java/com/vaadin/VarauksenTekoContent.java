package com.vaadin;


import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@SpringComponent
public class VarauksenTekoContent extends HorizontalLayout {
    @Autowired
    ElokuvaRepository elokuvaRepository;
    @Autowired
    NaytosRepository naytosRepository;
    @Autowired
    VarausRepository varausRepository;

    GridLayout sample = new GridLayout();
    Naytos naytos;
    Elokuva elokuva;
    Varaus varaus;
    ArrayList<int[]> varattavatPaikat;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        luoVarausnakyma();
    }

    private void luoVarausnakyma() {
        removeAllComponents();
        if (this.elokuva == null) {
            addComponent(new Label("Valitsemaasi näytöstä ei valitettavasti löytynyt..."));
        } else {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            addComponent(horizontalLayout);
            luoLeffanJaNaytoksenTiedot();

            varattavatPaikat = new ArrayList<>();

            VerticalLayout paikkaKartta = new VerticalLayout();
            int[][] paikat = muodostaTaulukkoPaikoista();
            luoPaikkaKartta(paikat.length, paikat[0].length, paikat);
            sample.addStyleName("outlined");
            sample.setSizeFull();

            Label valkokangas = new Label("!!!VALKOKANGAS!!!");
            paikkaKartta.addComponents(sample, valkokangas);
            valkokangas.setSizeFull();
            addComponent(paikkaKartta);

            Button teeVaraus = new Button("Suorita varaus");
            teeVaraus.addClickListener(click -> teeVaraukset());
            teeVaraus.addClickListener(click -> Notification.show("Varasit juuri "
                    + varattavatPaikat.size()
                    + " paikkaa elokuvaan " + elokuva.getNimi()
                    + " kello " + naytos.getKellonAika() + " " + naytos.getPaiva()));
            teeVaraus.addClickListener(click -> init());

            addComponent(teeVaraus);
        }
    }

    private void luoLeffanJaNaytoksenTiedot() {
        final HorizontalLayout leffaKortti = new HorizontalLayout();
        String nimi = elokuva.getNimi();
        String tyylilaji = elokuva.getTyylilaji();
        String kuvanOsoite = elokuva.getKuvanOsoite();

        String sali = naytos.getSali();
        String kellonAika = naytos.getKellonAika();
        Label vapaitaPaikkoja = new Label("Vapaita paikkoja jäljellä: " + laskeVapaitaPaikkoja());
        Label saliLab = new Label("Sali: " + sali);
        Label kellonAikaLab = new Label("Kello: " + kellonAika);
        Image kuva1 = new Image();
        kuva1.setSource(new ThemeResource(kuvanOsoite));

        final VerticalLayout leffatiedotright = new VerticalLayout();
        final VerticalLayout leffatiedotleft = new VerticalLayout();
        final Label Nimi = new Label(nimi);
        final Label Tyylilaji = new Label(tyylilaji);

        leffatiedotright.addComponents(Nimi, Tyylilaji, vapaitaPaikkoja, saliLab, kellonAikaLab);
        leffatiedotleft.addComponent(kuva1);
        leffaKortti.addComponents(leffatiedotleft, leffatiedotright);

        addComponent(leffaKortti);
    }

    private void luoPaikkaKartta(int rows, int columns, int[][] paikat) {
        sample.removeAllComponents();
        sample.setRows(rows);
        sample.setColumns(columns);

        for (int row = 0; row < sample.getRows(); row++) {
            for (int col = 0; col < sample.getColumns(); col++) {
                if (paikat[row][col] != 1) {
                    Button paikkaValinta = new Button(row + "   -    " + col);
                    final int i = row;
                    final int j = col;
                    paikkaValinta.addClickListener(click ->
                            lisaaPaikkaValinta(new int[]{i, j}));
                    paikkaValinta.addClickListener(click ->
                            paikkaValinta.setCaption("Valittu"));
                    paikkaValinta.addClickListener(click ->
                            Notification.show("Valitsit paikan: " + i + " - " + j));

                    sample.addComponent(paikkaValinta);
                    sample.setComponentAlignment(paikkaValinta, Alignment.MIDDLE_CENTER);
                } else {
                    Button varattu = new Button("-----");
                    sample.addComponent(varattu);
                    sample.setComponentAlignment(varattu, Alignment.MIDDLE_CENTER);
                }
                sample.setRowExpandRatio(row, 0.0f);
                sample.setColumnExpandRatio(col, 0.0f);
            }
        }
    }

    private void lisaaPaikkaValinta(int[] paikkaValinta) {
        boolean loytyyJo = false;
        for (int i = 0; i < this.varattavatPaikat.size(); i++) {
            if (this.varattavatPaikat.get(i)[0] == paikkaValinta[0] && this.varattavatPaikat.get(i)[1] == paikkaValinta[1]) {
                loytyyJo = true;
            }
        }
        if (!loytyyJo) {
            this.varattavatPaikat.add(paikkaValinta);
        }
    }

    protected void haeElokuvaKannasta(int id) {
        for (Elokuva e : elokuvaRepository.findAll()) {
            if (e.getId() == id) {
                this.elokuva = e;
            }
        }
    }

    protected void haeNaytosKannasta(int id) {
        for (Naytos n : naytosRepository.findAll()) {
            if (n.getId() == id) {
                this.naytos = n;
            }
        }
        haeElokuvaKannasta(Integer.parseInt(this.naytos.getElokuvanId()));
        luoVarausnakyma();
    }

    private int laskeVapaitaPaikkoja() {
        int paikkojaSalissa = Integer.parseInt(naytos.getPaikkojaRivilla()) * Integer.parseInt(naytos.getRiveja());
        int varauksia = 0;
        for (Varaus v : varausRepository.findAll()) {
            if (v.getNaytoksenId() == this.naytos.getId()) {
                varauksia++;
            }
        }
        return paikkojaSalissa - varauksia;
    }

    private int[][] muodostaTaulukkoPaikoista() {
        int paikkojaRivilla = Integer.parseInt(naytos.getPaikkojaRivilla());
        int riveja = Integer.parseInt(naytos.getRiveja());
        int[][] paikat = new int[riveja][paikkojaRivilla];

        for (Varaus v : varausRepository.findAll()) {
            if (v.getNaytoksenId() == this.naytos.getId()) {
                int r = v.getRivi();
                int p = v.getPaikka();
                paikat[r][p] = 1;
            }
        }
        return paikat;
    }

    private void teeVaraukset() {
        for (int i = 0; i < varattavatPaikat.size(); i++) {
            int rivi = varattavatPaikat.get(i)[0];
            int paikka = varattavatPaikat.get(i)[1];
            //TÄHÄN TARVITSEE VARAAJAN IDN SESSIOSTA
            Varaus v = new Varaus(1, elokuva.getNimi(), (int) naytos.getId(), rivi, paikka);
            varausRepository.save(v);
        }
    }
}
