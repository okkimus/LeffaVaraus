Harjoitustyön aihe on toteuttaa elokuvalippujen varaukseen tarkoitettu sovellus graafisella käyttöliittymällä.

Työn vaatimukset:

*Lipunmyyntijärjestelmä sisältää vähintään kaksi elokuvateatteria

*Elokuvateattereissa on vähintään kaksi erikokoista salia

*Käyttäjät kirjautuvat järjestelmään

*Asiakkaanja ylläpitäjän näkymät:

  *Ylläpitäjä
    *Elokuvien lisääminen, muokkaus ja poisto
    *Näytösten lisääminen, muokkaus ja poisto
    *Käyttäjien lisääminen (sovellusympäristöstäriippuen myös rekisteröintilomake uusille käyttäjille)

  *Asiakas
    *Omat varaukset
    *Varauksen muokkaus / poisto
    *Ohjelmiston selaaminen
    *Paikkojen varaaminen näytökseen
      *Elokuvateatteri, sali, näytös ja paikat
      *Graafinen näkymä (esim. salin pohjapiirros), josta käyttäjä voi valita paikat
      *Muiden käyttäjien varaamat paikat eivät ole valittavissa

*Tietojen lataaminen (tietojen tallennusta ohjelman suorituskertojen välillä ei vaaita)
  *Tiedot voi ladata esimerkiksi tekstitiedostosta (oma formaatti, xml, json...)
  *Yksi tiedoston rivi voi vastata yhtä tietuetta, joten esimerkiksi varaus voisi olla muotoa: teatteri;sali;näytös;paikka;käyttäjä
    *Valmiiden merkkauskielten (esim. xml) etuna ovat olemassaolevat jäsentimet


Tarvittavia asiota toteutettavaksi: Käyttäjä, Teatteri, Elokuva, Näytös, Sali(?), Paikka, Varaus

Käyttäjä: ID, nimi, käyttäjätunnus, salasana, varaukset[], admin
Teatteri: ID, nimi, sijainti, sali[], elokuva/ohjelmisto[], aukioloajat
Elokuva: ID, nimi, kesto, päänäyttelijä, näytökset[]
Näytös: ID, teatteri, sali, alkamasAika, varaukset[]
Sali: ID, koko, paikat[]
Paikka: ID, sali, varattu
Varaus: ID, käyttäjä, näytös, paikka[]

Metodeja

Käyttäjä: teeVaraus(elokuva, paikka); näytäVaraukset(); vaihdaSalasanaa();
Teatteri: naytaSalit(); naytaOhjelmisto();
Elokuva: naytaNaytokset();
Näytös: naytaVaraukset(); varausTilanne();
Varaus: naytaVaratutPaikat();
