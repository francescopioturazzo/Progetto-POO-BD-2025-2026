package model;

/**
 * Rappresenta uno scooter gestito dal sistema di noleggio.
 * Contiene le informazioni principali del veicolo come cilindrata,
 * targa, marca, modello e stato attuale.
 *
 * <p>Questa classe estende {@link Veicolo} e aggiunge l'attributo
 * specifico della cilindrata.</p>
 *
 * <p>È un modello dati utilizzato da GUI, controller e DAO.</p>
 *
 * @see model.Veicolo
 * @see model.Noleggio
 *
 * @author Francesco & Vincenzo
 */
public class Scooter extends Veicolo
{

    /** Cilindrata dello scooter (es. 125cc, 300cc). */
    private int cilindrata;

    /**
     * Costruttore vuoto, utile per inizializzazioni progressive.
     */
    public Scooter()
    {
    }

    /**
     * Costruttore completo con tutti gli attributi dello scooter.
     *
     * @param id identificativo del veicolo
     * @param targa targa dello scooter
     * @param marca marca dello scooter
     * @param modello modello dello scooter
     * @param cilindrata cilindrata dello scooter
     * @param stato stato attuale del veicolo
     */
    public Scooter(int id, String targa, String marca, String modello, int cilindrata, String stato)
    {
        super(id, marca, modello, targa, stato);
        this.cilindrata = cilindrata;
    }

    /**
     * Restituisce l'identificativo del veicolo.
     *
     * @return id del veicolo
     */
    public int getId()
    {
        return super.getIdentificativoVeicolo();
    }

    /**
     * Restituisce la targa dello scooter.
     *
     * @return targa del veicolo
     */
    public String getTarga()
    {
        return super.getTargaVeicolo();
    }

    /**
     * Restituisce la marca dello scooter.
     *
     * @return marca del veicolo
     */
    public String getMarca()
    {
        return super.getMarcaVeicolo();
    }

    /**
     * Restituisce il modello dello scooter.
     *
     * @return modello del veicolo
     */
    public String getModello()
    {
        return super.getModelloVeicolo();
    }

    /**
     * Restituisce la cilindrata dello scooter.
     *
     * @return cilindrata in cc
     */
    public int getCilindrata()
    {
        return cilindrata;
    }

    /**
     * Imposta la cilindrata dello scooter.
     *
     * @param cilindrata nuova cilindrata
     */
    public void setCilindrata(int cilindrata)
    {
        this.cilindrata = cilindrata;
    }

    /**
     * Restituisce lo stato attuale dello scooter.
     *
     * @return stato del veicolo
     */
    public String getStato()
    {
        return super.getStatoAttualeVeicolo();
    }

    /**
     * Restituisce una rappresentazione testuale dello scooter,
     * utile per la visualizzazione nelle interfacce grafiche.
     *
     * @return stringa contenente marca, modello, targa, cilindrata e stato
     */
    @Override
    public String toString()
    {
        return getMarca() + " " + getModello() + " (" + getTarga() + ") - " +
                cilindrata + "cc - Stato: " + getStato();
    }
}
