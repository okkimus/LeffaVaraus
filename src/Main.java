
public class Main {
    public static void main(String[] args) {
        Kayttaja[] kayttajat = luoKayttajia();
        Teatteri[] teatterit = luoTeatterit();
        Elokuva[] elokuvat = luoElokuvat();

        for (int i = 0; i < kayttajat.length; i++) {
            System.out.println(kayttajat[i]);
        }
        for (int i = 0; i < teatterit.length; i++) {
            System.out.println(teatterit[i]);
        }
        for (int i = 0; i < elokuvat.length; i++) {
            System.out.println(elokuvat[i]);
        }
    }

    public static Kayttaja[] luoKayttajia() {
        Kayttaja[] lista = new Kayttaja[3];
        Kayttaja k0 = new Kayttaja("Mikko", "Okkimus", "Salasana", false);
        Kayttaja k1 = new Kayttaja("Miko", "Okkimu", "Salasana", false);
        Kayttaja k2 = new Kayttaja("Mko", "Okks", "Salasana", false);
        lista[0] = k0;
        lista[1] = k1;
        lista[2] = k2;
        return lista;
    }

    public static Teatteri[] luoTeatterit() {
        Teatteri[] lista = new Teatteri[2];
        Teatteri t0 = new Teatteri("Turku", "Kello 10-22 Ma-Pe");
        Teatteri t1 = new Teatteri("Helsinki", "Kello 9-23 Ma-Su");
        lista[0] = t0;
        lista[1] = t1;
        return lista;
    }

    public static Elokuva[] luoElokuvat() {
        Elokuva[] lista = new Elokuva[3];
        Elokuva e0 = new Elokuva("Revenant", "DiCaprio", 190);
        Elokuva e1 = new Elokuva("Inception", "DiCaprio", 150);
        Elokuva e2 = new Elokuva("Tom of Finland", "Strang", 120);
        lista[0] = e0;
        lista[1] = e1;
        lista[2] = e2;
        return lista;
    }


}
