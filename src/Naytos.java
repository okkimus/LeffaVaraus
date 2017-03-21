import java.util.Arrays;

public class Naytos {
    private static int counter = 0;

    private int id;
    private Elokuva elokuva;
    private Sali sali;
    private String alkamisAika;
    private Varaus[] varaukset;
    private int[][] paikat;

    public Naytos(String alkamisAika, Sali sali, Elokuva elokuva) {
        this.id = ++counter;
        this.sali = sali;
        this.alkamisAika = alkamisAika;
        this.elokuva = elokuva;
        this.paikat = sali.getPaikat();
    }

    @Override
    public String toString() {
        return "Naytos{" +
                "elokuva=" + elokuva +
                ", sali=" + sali +
                ", alkamisAika='" + alkamisAika + '\'' +
                ", varaukset=" + Arrays.toString(varaukset) +
                '}';
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

    public void tulostaPaikkaKartta() {
        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[i].length; j++) {
                System.out.print(this.paikat[i][j] + "-");
            }
            System.out.println();
        }
    }
}
