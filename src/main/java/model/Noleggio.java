package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Rappresenta un noleggio effettuato da un cliente su un veicolo.
 * Contiene tutte le informazioni utili per descrivere il noleggio:
 * cliente, veicolo, date di inizio e fine, tipo di veicolo e costo.
 *
 * <p>Le date vengono gestite come {@link LocalDate} e convertite
 * da/verso stringhe nel formato "yyyy-MM-dd" per compatibilità con il database.</p>
 *
 * <p>Questa classe è un modello dati utilizzato da GUI, controller e DAO.</p>
 *
 * @see model.Cliente
 * @see model.Auto
 *
 * @author Francesco & Vincenzo
 */
public class Noleggio
{

    /** Identificativo univoco del noleggio nel database. */
    private int id;

    /** Identificativo del cliente che ha effettuato il noleggio. */
    private int idCliente;

    /** Identificativo del veicolo noleggiato. */
    private int idVeicolo;

    /** Tipo del veicolo (es. auto, scooter). */
    private String tipoVeicolo;

    /** Data di inizio del noleggio. */
    private LocalDate dataInizio;

    /** Data di fine del noleggio (può essere null se il noleggio è ancora attivo). */
    private LocalDate dataFine;

    /** Costo totale del noleggio. */
    private double costo;

    /** Formattatore per la conversione delle date nel formato "yyyy-MM-dd". */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Costruttore vuoto, utile per inizializzazioni progressive.
     */
    public Noleggio()
    {
    }

    /**
     * Costruttore completo con tutti gli attributi.
     *
     * @param id identificativo del noleggio
     * @param idCliente identificativo del cliente
     * @param idVeicolo identificativo del veicolo
     * @param tipoVeicolo tipo del veicolo (auto, scooter, ecc.)
     * @param dataInizio data di inizio del noleggio
     * @param dataFine data di fine del noleggio (può essere null)
     * @param costo costo totale del noleggio
     */
    public Noleggio(int id, int idCliente, int idVeicolo, String tipoVeicolo,
                    LocalDate dataInizio, LocalDate dataFine, double costo)
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idVeicolo = idVeicolo;
        this.tipoVeicolo = tipoVeicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costo = costo;
    }

    /**
     * Costruttore compatibile con il database quando le date sono fornite come stringhe.
     *
     * @param id identificativo del noleggio
     * @param idCliente identificativo del cliente
     * @param idVeicolo identificativo del veicolo
     * @param dataInizioString data di inizio in formato stringa "yyyy-MM-dd"
     * @param dataFineString data di fine in formato stringa (può essere null o vuota)
     * @param costo costo totale del noleggio
     */
    public Noleggio(int id, int idCliente, int idVeicolo,
                    String dataInizioString, String dataFineString, double costo)
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idVeicolo = idVeicolo;
        this.tipoVeicolo = "";

        this.dataInizio = LocalDate.parse(dataInizioString, FORMATTER);

        if (dataFineString == null || dataFineString.isEmpty())
        {
            this.dataFine = null;
        }
        else
        {
            this.dataFine = LocalDate.parse(dataFineString, FORMATTER);
        }

        this.costo = costo;
    }

    /**
     * Restituisce la data di inizio del noleggio in formato stringa.
     *
     * @return data di inizio nel formato "yyyy-MM-dd"
     */
    public String getDataInizioString()
    {
        return dataInizio.format(FORMATTER);
    }

    /**
     * Restituisce la data di fine del noleggio in formato stringa.
     *
     * @return data di fine nel formato "yyyy-MM-dd", oppure "—" se il noleggio è ancora attivo
     */
    public String getDataFineString()
    {
        if (dataFine == null)
        {
            return "—";
        }
        return dataFine.format(FORMATTER);
    }

    /** @return id del noleggio */
    public int getId()
    {
        return id;
    }

    /** @param id nuovo identificativo del noleggio */
    public void setId(int id)
    {
        this.id = id;
    }

    /** @return id del cliente */
    public int getIdCliente()
    {
        return idCliente;
    }

    /** @param idCliente nuovo identificativo del cliente */
    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    /** @return id del veicolo */
    public int getIdVeicolo()
    {
        return idVeicolo;
    }

    /** @param idVeicolo nuovo identificativo del veicolo */
    public void setIdVeicolo(int idVeicolo)
    {
        this.idVeicolo = idVeicolo;
    }

    /** @return tipo del veicolo */
    public String getTipoVeicolo()
    {
        return tipoVeicolo;
    }

    /** @param tipoVeicolo nuovo tipo del veicolo */
    public void setTipoVeicolo(String tipoVeicolo)
    {
        this.tipoVeicolo = tipoVeicolo;
    }

    /** @return data di inizio del noleggio */
    public LocalDate getDataInizio()
    {
        return dataInizio;
    }

    /** @param dataInizio nuova data di inizio */
    public void setDataInizio(LocalDate dataInizio)
    {
        this.dataInizio = dataInizio;
    }

    /** @return data di fine del noleggio */
    public LocalDate getDataFine()
    {
        return dataFine;
    }

    /** @param dataFine nuova data di fine */
    public void setDataFine(LocalDate dataFine)
    {
        this.dataFine = dataFine;
    }

    /** @return costo totale del noleggio */
    public double getCosto()
    {
        return costo;
    }

    /** @param costo nuovo costo del noleggio */
    public void setCosto(double costo)
    {
        this.costo = costo;
    }

    /**
     * Restituisce una rappresentazione testuale del noleggio,
     * utile per la visualizzazione nelle interfacce grafiche.
     *
     * @return stringa contenente id, cliente, veicolo, date e costo
     */
    @Override
    public String toString()
    {
        return id + " - Cliente: " + idCliente +
                " - Veicolo: " + idVeicolo +
                " (" + tipoVeicolo + ")" +
                " - " + getDataInizioString() + " → " + getDataFineString() +
                " - " + costo + "€";
    }
}