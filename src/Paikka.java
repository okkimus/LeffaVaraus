
public class Paikka {
    private static int counter = 0;

    private int id;
    private int rivi;
    private int paikka;
    private boolean varattu;

    public Paikka(int rivi, int paikka) {
        this.id = ++counter;
        this.rivi = rivi;
        this.paikka = paikka;
        this.varattu = false;
    }

    public int getId() {
        return id;
    }

    public int getRivi() {
        return rivi;
    }

    public void setRivi(int rivi) {
        this.rivi = rivi;
    }

    public int getPaikka() {
        return paikka;
    }

    public void setPaikka(int paikka) {
        this.paikka = paikka;
    }

    public boolean isVarattu() {
        return varattu;
    }

    public void setVarattu(boolean varattu) {
        this.varattu = varattu;
    }
}
