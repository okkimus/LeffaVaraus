import java.util.Arrays;

public class Teatteri {
    private static int counter = 0;

    private int id;
    private String nimi;
    private String sijainti;
    private String aukioloajat;
    private Sali[] salit;
    private Naytos[] ohjelmisto;

    public Teatteri(String sijainti, String aukioloajat) {
        this.id = ++counter;
        this.sijainti = sijainti;
        this.aukioloajat = aukioloajat;
    }

    public int getId() {
        return id;
    }

    public String getSijainti() {
        return sijainti;
    }

    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    public String getAukioloajat() {
        return aukioloajat;
    }

    public void setAukioloajat(String aukioloajat) {
        this.aukioloajat = aukioloajat;
    }

    public Sali[] getSalit() {
        return salit;
    }

    public void setSalit(Sali[] salit) {
        this.salit = salit;
    }

    public Naytos[] getOhjelmisto() {
        return ohjelmisto;
    }

    public void setOhjelmisto(Naytos[] ohjelmisto) {
        this.ohjelmisto = ohjelmisto;
    }

    @Override
    public String toString() {
        return "Teatteri{" +
                "nimi='" + nimi + '\'' +
                ", sijainti='" + sijainti + '\'' +
                ", aukioloajat='" + aukioloajat + '\'' +
                ", salit=" + Arrays.toString(salit) +
                ", ohjelmisto=" + Arrays.toString(ohjelmisto) +
                '}';
    }
}
