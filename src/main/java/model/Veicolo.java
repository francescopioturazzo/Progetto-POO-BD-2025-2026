package model;

public abstract class Veicolo {

    private int identificativoVeicolo;
    private String marcaVeicolo;
    private String modelloVeicolo;
    private String targaVeicolo;

    public enum StatoVeicolo {
        DISPONIBILE,
        NOLEGGIATO,
        IN_MANUTENZIONE
    }

    private StatoVeicolo statoAttualeVeicolo;

    public Veicolo() {}

    public Veicolo(int identificativoVeicolo, String marcaVeicolo, String modelloVeicolo,
                   String targaVeicolo, String statoAttualeVeicolo) {

        this.identificativoVeicolo = identificativoVeicolo;
        this.marcaVeicolo = marcaVeicolo;
        this.modelloVeicolo = modelloVeicolo;
        this.targaVeicolo = targaVeicolo;

        String statoConvertito = statoAttualeVeicolo.toUpperCase().replace(" ", "_");

        if (statoConvertito.equals("NON_DISPONIBILE")) {
            statoConvertito = "NOLEGGIATO";
        }

        if (statoConvertito.equals("MANUTENZIONE")) {
            statoConvertito = "IN_MANUTENZIONE";
        }

        this.statoAttualeVeicolo = StatoVeicolo.valueOf(statoConvertito);
    }

    public int getIdentificativoVeicolo() {
        return identificativoVeicolo;
    }

    public void setIdentificativoVeicolo(int nuovoIdentificativoVeicolo) {
        this.identificativoVeicolo = nuovoIdentificativoVeicolo;
    }

    public String getMarcaVeicolo() {
        return marcaVeicolo;
    }

    public void setMarcaVeicolo(String nuovaMarcaVeicolo) {
        this.marcaVeicolo = nuovaMarcaVeicolo;
    }

    public String getModelloVeicolo() {
        return modelloVeicolo;
    }

    public void setModelloVeicolo(String nuovoModelloVeicolo) {
        this.modelloVeicolo = nuovoModelloVeicolo;
    }

    public String getTargaVeicolo() {
        return targaVeicolo;
    }

    public void setTargaVeicolo(String nuovaTargaVeicolo) {
        this.targaVeicolo = nuovaTargaVeicolo;
    }

    public String getStatoAttualeVeicolo() {
        return statoAttualeVeicolo.name();
    }

    public void setStatoAttualeVeicolo(String nuovoStatoAttualeVeicolo) {

        String statoConvertito = nuovoStatoAttualeVeicolo.toUpperCase().replace(" ", "_");

        if (statoConvertito.equals("NON_DISPONIBILE")) {
            statoConvertito = "NOLEGGIATO";
        }

        if (statoConvertito.equals("MANUTENZIONE")) {
            statoConvertito = "IN_MANUTENZIONE";
        }

        this.statoAttualeVeicolo = StatoVeicolo.valueOf(statoConvertito);
    }

    public boolean verificaDisponibilitaVeicolo() {
        return statoAttualeVeicolo == StatoVeicolo.DISPONIBILE;
    }

    public void impostaVeicoloComeNoleggiato() {
        this.statoAttualeVeicolo = StatoVeicolo.NOLEGGIATO;
    }

    public void impostaVeicoloComeDisponibile() {
        this.statoAttualeVeicolo = StatoVeicolo.DISPONIBILE;
    }

    public void impostaVeicoloInManutenzione() {
        this.statoAttualeVeicolo = StatoVeicolo.IN_MANUTENZIONE;
    }

    @Override
    public String toString() {
        return identificativoVeicolo + " - " + marcaVeicolo + " " + modelloVeicolo + " (" + targaVeicolo + ")";
    }
}
