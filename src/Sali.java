
public class Sali {
    private static int counter = 0;

    private int id;
    private int kapasiteetti;
    private Teatteri teatteri;
    private Paikka[] paikat;

    public Sali(int kapasiteetti, Teatteri teatteri) {
        this.id = ++counter;
        this.kapasiteetti = kapasiteetti;
        this.teatteri = teatteri;
        this.paikat = new Paikka[kapasiteetti];
    }

    public int getId() {
        return id;
    }

    public int getKapasiteetti() {
        return kapasiteetti;
    }

    public void setKapasiteetti(int kapasiteetti) {
        this.kapasiteetti = kapasiteetti;
    }

    public Teatteri getTeatteri() {
        return teatteri;
    }

    public void setTeatteri(Teatteri teatteri) {
        this.teatteri = teatteri;
    }

    public Paikka[] getPaikat() {
        return paikat;
    }

    public void setPaikat(Paikka[] paikat) {
        this.paikat = paikat;
    }
}
