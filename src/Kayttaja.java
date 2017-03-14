import java.util.Arrays;

public class Kayttaja {
    private static int counter = 0;

    private int id;
    private String nimi;
    private String kayttajatunnus;
    private String salasana;
    private Varaus[] varaukset;
    private boolean admin;

    public Kayttaja(String nimi, String kayttajatunnus, String salasana, boolean admin) {
        this.nimi = nimi;
        this. kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
        this.admin = admin;
        this.id = ++counter;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public Varaus[] getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(Varaus[] varaukset) {
        this.varaukset = varaukset;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Kayttaja{" +
                "nimi='" + nimi + '\'' +
                ", kayttajatunnus='" + kayttajatunnus + '\'' +
                ", salasana='" + salasana + '\'' +
                ", varaukset=" + Arrays.toString(varaukset) +
                ", admin=" + admin +
                '}';
    }
}
