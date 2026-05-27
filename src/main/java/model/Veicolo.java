package model;

public class Veicolo {

    private final int idVeicolo;
    private String marca;
    private String modello;
    private String targa;
    private enum Stato
    {
        DISPONIBILE,
        NOLEGGIATO,
        MANUTENZIONE;
    };
    private Stato stato;

    public Veicolo(int idVeicolo, String marca, String modello, String targa, String stato) {
        this.idVeicolo = idVeicolo;
        this.marca = marca;
        this.modello = modello;
        this.targa = targa;
        this.stato = Stato.valueOf(stato.toUpperCase());
    }
    public int getIdVeicolo(){
        return idVeicolo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModello() {
        return modello;
    }
    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getStato() {
        return stato.name();
    }
    public void setStato(String stato) {
        this.stato = Stato.valueOf(stato.toUpperCase());
    }

    public boolean isDisponibile (Veicolo veicoloDaNoleggiare)
    {
        if (veicoloDaNoleggiare.getStato().equals("DISPONIBILE"))
        {
            System.out.println("è disponibile");
            return true;
        } else return false;
    }
    @Override
    public String toString() {
        return idVeicolo + "-" +marca+" "+modello+ "(" + targa + ")";
    }
}
