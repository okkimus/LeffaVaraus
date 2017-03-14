
public class Naytos {
    private static int counter = 0;

    private int id;
    private Sali sali;
    private String alkamisAika;
    private Varaus[] varaukset;

    public Naytos(String alkamisAika, Sali sali) {
        this.id = ++counter;
        this.sali = sali;
        this.alkamisAika = alkamisAika;
    }

    public int getId() {
        return id;
    }

    public Sali getSali() {
        return sali;
    }

    public void setSali(Sali sali) {
        this.sali = sali;
    }

    public String getAlkamisAika() {
        return alkamisAika;
    }

    public void setAlkamisAika(String alkamisAika) {
        this.alkamisAika = alkamisAika;
    }

    public Varaus[] getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(Varaus[] varaukset) {
        this.varaukset = varaukset;
    }
}
