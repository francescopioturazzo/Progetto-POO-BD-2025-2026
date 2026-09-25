package model;

/**
 * Rappresenta un'automobile gestita dal sistema di noleggio.
 * La classe contiene tutte le informazioni principali relative al veicolo,
 * come identificativo, targa, marca, modello, numero di porte e stato attuale.
 *
 * <p>Lo stato dell'auto indica la disponibilità del veicolo e può assumere
 * valori come "disponibile", "noleggiata", "manutenzione", ecc.</p>
 *
 * <p>Questa classe funge da semplice modello dati (POJO) utilizzato
 * per trasferire informazioni tra GUI, controller e DAO.</p>
 *
 * @see model.Noleggio
 * @see model.Cliente
 *
 * @author Francesco & Vincenzo
 */
public class Auto
{

    /** Identificativo univoco dell'auto nel database. */
    private int id;

    /** Targa dell'auto, unica per ogni veicolo. */
    private String targa;

    /** Marca dell'auto (es. Fiat, BMW, Audi). */
    private String marca;

    /** Modello dell'auto (es. Panda, Serie 3, A3). */
    private String modello;

    /** Numero di porte dell'auto. */
    private int porte;

    /** Stato attuale dell'auto (es. disponibile, noleggiata, manutenzione). */
    private String stato;

    /**
     * Costruisce un nuovo oggetto Auto con tutti i suoi attributi.
     *
     * @param id identificativo univoco dell'auto
     * @param targa targa del veicolo
     * @param marca marca del veicolo
     * @param modello modello del veicolo
     * @param porte numero di porte
     * @param stato stato attuale del veicolo
     */
    public Auto(int id, String targa, String marca, String modello, int porte, String stato)
    {
        this.id = id;
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.porte = porte;
        this.stato = stato;
    }

    /**
     * Restituisce l'identificativo dell'auto.
     *
     * @return id dell'auto
     */
    public int getId()
    {
        return id;
    }

    /**
     * Restituisce la targa dell'auto.
     *
     * @return targa del veicolo
     */
    public String getTarga()
    {
        return targa;
    }

    /**
     * Restituisce la marca dell'auto.
     *
     * @return marca del veicolo
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * Restituisce il modello dell'auto.
     *
     * @return modello del veicolo
     */
    public String getModello()
    {
        return modello;
    }

    /**
     * Restituisce il numero di porte dell'auto.
     *
     * @return numero di porte
     */
    public int getPorte()
    {
        return porte;
    }

    /**
     * Restituisce lo stato attuale dell'auto.
     *
     * @return stato del veicolo
     */
    public String getStato()
    {
        return stato;
    }

    /**
     * Imposta l'identificativo dell'auto.
     *
     * @param id nuovo identificativo
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Imposta la targa dell'auto.
     *
     * @param targa nuova targa
     */
    public void setTarga(String targa)
    {
        this.targa = targa;
    }

    /**
     * Imposta la marca dell'auto.
     *
     * @param marca nuova marca
     */
    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    /**
     * Imposta il modello dell'auto.
     *
     * @param modello nuovo modello
     */
    public void setModello(String modello)
    {
        this.modello = modello;
    }

    /**
     * Imposta il numero di porte dell'auto.
     *
     * @param porte nuovo numero di porte
     */
    public void setPorte(int porte)
    {
        this.porte = porte;
    }

    /**
     * Imposta lo stato attuale dell'auto.
     *
     * @param stato nuovo stato del veicolo
     */
    public void setStato(String stato)
    {
        this.stato = stato;
    }

    /**
     * Restituisce una rappresentazione testuale dell'auto,
     * utile per la visualizzazione nelle interfacce grafiche.
     *
     * @return stringa contenente id, targa, marca, modello, porte e stato
     */
    @Override
    public String toString()
    {
        return id + " - " + targa + " - " + marca + " - " + modello + " - " + porte + " porte - " + stato;
    }
}
