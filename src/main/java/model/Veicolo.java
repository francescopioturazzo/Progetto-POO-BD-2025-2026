package model;

/**
 * Classe astratta che rappresenta un veicolo nel sistema di noleggio.
 * Contiene le informazioni comuni a tutti i veicoli: identificativo,
 * marca, modello, targa e stato attuale.
 *
 * <p>Lo stato del veicolo viene gestito tramite un enum interno
 * che indica se il veicolo è disponibile, noleggiato o in manutenzione.</p>
 *
 * <p>Questa classe è un modello dati utilizzato da GUI, controller e DAO
 * ed è estesa da classi specifiche come Auto e Scooter.</p>
 *
 * @see model.Auto
 * @see model.Scooter
 * @see model.Noleggio
 *
 * @author Francesco & Vincenzo
 */
public abstract class Veicolo
{

    /** Identificativo univoco del veicolo nel database. */
    private int identificativoVeicolo;

    /** Marca del veicolo (es. Honda, Yamaha, Fiat). */
    private String marcaVeicolo;

    /** Modello del veicolo (es. SH, Panda, TMAX). */
    private String modelloVeicolo;

    /** Targa del veicolo. */
    private String targaVeicolo;

    /**
     * Stati possibili del veicolo.
     * DISPONIBILE → il veicolo può essere noleggiato
     * NOLEGGIATO → il veicolo è attualmente in uso
     * IN_MANUTENZIONE → il veicolo non è disponibile
     */
    public enum StatoVeicolo
    {
        DISPONIBILE,
        NOLEGGIATO,
        IN_MANUTENZIONE
    }

    /** Stato attuale del veicolo. */
    private StatoVeicolo statoAttualeVeicolo;

    /**
     * Costruttore vuoto, utile per inizializzazioni progressive.
     */
    public Veicolo()
    {
    }

    /**
     * Costruttore completo con tutti gli attributi del veicolo.
     * Converte automaticamente lo stato da stringa a enum.
     *
     * @param identificativoVeicolo identificativo del veicolo
     * @param marcaVeicolo marca del veicolo
     * @param modelloVeicolo modello del veicolo
     * @param targaVeicolo targa del veicolo
     * @param statoAttualeVeicolo stato del veicolo in formato stringa
     */
    public Veicolo(int identificativoVeicolo, String marcaVeicolo, String modelloVeicolo,
                   String targaVeicolo, String statoAttualeVeicolo)
    {
        this.identificativoVeicolo = identificativoVeicolo;
        this.marcaVeicolo = marcaVeicolo;
        this.modelloVeicolo = modelloVeicolo;
        this.targaVeicolo = targaVeicolo;

        String statoConvertito = statoAttualeVeicolo.toUpperCase().replace(" ", "_");

        if (statoConvertito.equals("NON_DISPONIBILE"))
        {
            statoConvertito = "NOLEGGIATO";
        }

        if (statoConvertito.equals("MANUTENZIONE"))
        {
            statoConvertito = "IN_MANUTENZIONE";
        }

        this.statoAttualeVeicolo = StatoVeicolo.valueOf(statoConvertito);
    }

    /** @return identificativo del veicolo */
    public int getIdentificativoVeicolo()
    {
        return identificativoVeicolo;
    }

    /** @param nuovoIdentificativoVeicolo nuovo identificativo del veicolo */
    public void setIdentificativoVeicolo(int nuovoIdentificativoVeicolo)
    {
        this.identificativoVeicolo = nuovoIdentificativoVeicolo;
    }

    /** @return marca del veicolo */
    public String getMarcaVeicolo()
    {
        return marcaVeicolo;
    }

    /** @param nuovaMarcaVeicolo nuova marca del veicolo */
    public void setMarcaVeicolo(String nuovaMarcaVeicolo)
    {
        this.marcaVeicolo = nuovaMarcaVeicolo;
    }

    /** @return modello del veicolo */
    public String getModelloVeicolo()
    {
        return modelloVeicolo;
    }

    /** @param nuovoModelloVeicolo nuovo modello del veicolo */
    public void setModelloVeicolo(String nuovoModelloVeicolo)
    {
        this.modelloVeicolo = nuovoModelloVeicolo;
    }

    /** @return targa del veicolo */
    public String getTargaVeicolo()
    {
        return targaVeicolo;
    }

    /** @param nuovaTargaVeicolo nuova targa del veicolo */
    public void setTargaVeicolo(String nuovaTargaVeicolo)
    {
        this.targaVeicolo = nuovaTargaVeicolo;
    }

    /**
     * Restituisce lo stato attuale del veicolo come stringa.
     *
     * @return stato del veicolo
     */
    public String getStatoAttualeVeicolo()
    {
        return statoAttualeVeicolo.name();
    }

    /**
     * Imposta lo stato del veicolo convertendo la stringa in enum.
     *
     * @param nuovoStatoAttualeVeicolo nuovo stato del veicolo
     */
    public void setStatoAttualeVeicolo(String nuovoStatoAttualeVeicolo)
    {
        String statoConvertito = nuovoStatoAttualeVeicolo.toUpperCase().replace(" ", "_");

        if (statoConvertito.equals("NON_DISPONIBILE"))
        {
            statoConvertito = "NOLEGGIATO";
        }

        if (statoConvertito.equals("MANUTENZIONE"))
        {
            statoConvertito = "IN_MANUTENZIONE";
        }

        this.statoAttualeVeicolo = StatoVeicolo.valueOf(statoConvertito);
    }

    /**
     * Verifica se il veicolo è disponibile per il noleggio.
     *
     * @return true se disponibile, false altrimenti
     */
    public boolean verificaDisponibilitaVeicolo()
    {
        return statoAttualeVeicolo == StatoVeicolo.DISPONIBILE;
    }

    /** Imposta il veicolo come noleggiato. */
    public void impostaVeicoloComeNoleggiato()
    {
        this.statoAttualeVeicolo = StatoVeicolo.NOLEGGIATO;
    }

    /** Imposta il veicolo come disponibile. */
    public void impostaVeicoloComeDisponibile()
    {
        this.statoAttualeVeicolo = StatoVeicolo.DISPONIBILE;
    }

    /** Imposta il veicolo come in manutenzione. */
    public void impostaVeicoloInManutenzione()
    {
        this.statoAttualeVeicolo = StatoVeicolo.IN_MANUTENZIONE;
    }

    /**
     * Restituisce una rappresentazione testuale del veicolo.
     *
     * @return stringa con id, marca, modello e targa
     */
    @Override
    public String toString()
    {
        return identificativoVeicolo + " - " + marcaVeicolo + " " + modelloVeicolo + " (" + targaVeicolo + ")";
    }
}
