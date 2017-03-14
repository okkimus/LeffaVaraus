import java.util.Arrays;

public class Elokuva {
    private static int counter = 0;

    private int id;
    private String nimi;
    private String paaosassa;
    private int kesto;
    private Naytos[] naytokset;

    public Elokuva(String nimi, String paaosassa, int kesto) {
        this.id = ++counter;
        this.nimi = nimi;
        this.paaosassa = paaosassa;
        this.kesto = kesto;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getPaaosassa() {
        return paaosassa;
    }

    public void setPaaosassa(String paaosassa) {
        this.paaosassa = paaosassa;
    }

    public int getKesto() {
        return kesto;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    public Naytos[] getNaytokset() {
        return naytokset;
    }

    public void setNaytokset(Naytos[] naytokset) {
        this.naytokset = naytokset;
    }

    @Override
    public String toString() {
        return "Elokuva{" +
                "nimi='" + nimi + '\'' +
                ", paaosassa='" + paaosassa + '\'' +
                ", kesto=" + kesto +
                ", naytokset=" + Arrays.toString(naytokset) +
                '}';
    }
}
