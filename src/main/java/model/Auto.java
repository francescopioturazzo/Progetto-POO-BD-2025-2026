package model;

public class Auto {

    private int id;
    private String targa;
    private String marca;
    private String modello;
    private int porte;
    private String stato;

    public Auto(int id, String targa, String marca, String modello, int porte, String stato) {
        this.id = id;
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.porte = porte;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public String getTarga() {
        return targa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public int getPorte() {
        return porte;
    }

    public String getStato() {
        return stato;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return id + " - " + targa + " - " + marca + " - " + modello + " - " + porte + " porte - " + stato;
    }
}
