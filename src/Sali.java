import java.util.Arrays;

public class Sali {
    private static int counter = 0;

    private int id;
    private int kapasiteetti;
    private Teatteri teatteri;
    private int[][] paikat;
    //private Paikka[] paikat;

    public Sali(int kapasiteetti, Teatteri teatteri, int rivit, int paikatRivilla) {
        this.id = ++counter;
        this.kapasiteetti = kapasiteetti;
        this.teatteri = teatteri;
        this.paikat = new int[rivit][paikatRivilla];
        setPaikat(rivit, paikatRivilla);
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

    public int[][] getPaikat() {
        return paikat;
    }

    public void setPaikat(int rivienMaara, int paikkojaRivilla) {
        for (int i = 0; i < rivienMaara; i++) {
            for (int j = 0; j < paikkojaRivilla; j++) {
                this.paikat[i][j] = 0;
            }
        }
    }
}
