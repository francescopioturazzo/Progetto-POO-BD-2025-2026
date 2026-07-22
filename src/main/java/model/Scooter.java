package model;

public class Scooter extends Veicolo {

    private int cilindrata;

    public Scooter() {}

    public Scooter(int id, String targa, String marca, String modello, int cilindrata, String stato) {
        super(id, marca, modello, targa, stato);
        this.cilindrata = cilindrata;
    }

    // Getter e Setter compatibili con GUI e DAO
    public int getId() {
        return super.getIdentificativoVeicolo();
    }

    public String getTarga() {
        return super.getTargaVeicolo();
    }

    public String getMarca() {
        return super.getMarcaVeicolo();
    }

    public String getModello() {
        return super.getModelloVeicolo();
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getStato() {
        return super.getStatoAttualeVeicolo();
    }

    @Override
    public String toString() {
        return getMarca() + " " + getModello() + " (" + getTarga() + ") - " + cilindrata + "cc - Stato: " + getStato();
    }
}
