package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Rappresenta un pagamento effettuato da un cliente.
 * Contiene informazioni come metodo di pagamento, importo, data
 * e il cliente che ha effettuato il pagamento.
 *
 * <p>La data viene gestita come {@link LocalDate} e convertita
 * da/verso stringhe nel formato "yyyy-MM-dd" per compatibilità con il database.</p>
 *
 * <p>Questa classe è un modello dati utilizzato da GUI, controller e DAO.</p>
 *
 * @see model.Cliente
 *
 * @author Francesco & Vincenzo
 */
public class Pagamento
{

    /** Identificativo univoco del pagamento nel database. */
    private int id;

    /** Metodo di pagamento (es. carta, contanti, PayPal). */
    private String metodo;

    /** Importo pagato dal cliente. */
    private double importo;

    /** Data in cui è stato effettuato il pagamento. */
    private LocalDate dataPagamento;

    /** Identificativo del cliente che ha effettuato il pagamento. */
    private int idCliente;

    /** Formattatore per la conversione delle date nel formato "yyyy-MM-dd". */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Costruttore vuoto, utile per inizializzazioni progressive.
     */
    public Pagamento()
    {
    }

    /**
     * Costruttore usato dal DAO quando la data è già un oggetto LocalDate.
     *
     * @param id identificativo del pagamento
     * @param metodo metodo di pagamento
     * @param importo importo pagato
     * @param dataPagamento data del pagamento
     */
    public Pagamento(int id, String metodo, double importo, LocalDate dataPagamento)
    {
        this.id = id;
        this.metodo = metodo;
        this.importo = importo;
        this.dataPagamento = dataPagamento;
    }

    /**
     * Costruttore compatibile con la GUI quando la data è fornita come stringa.
     *
     * @param id identificativo del pagamento
     * @param importo importo pagato
     * @param dataPagamentoString data del pagamento in formato stringa "yyyy-MM-dd"
     * @param metodo metodo di pagamento
     * @param idCliente identificativo del cliente
     */
    public Pagamento(int id, double importo, String dataPagamentoString, String metodo, int idCliente)
    {
        this.id = id;
        this.importo = importo;
        this.metodo = metodo;
        this.idCliente = idCliente;
        this.dataPagamento = LocalDate.parse(dataPagamentoString, FORMATTER);
    }

    /**
     * Restituisce la data del pagamento in formato stringa.
     *
     * @return data nel formato "yyyy-MM-dd"
     */
    public String getDataPagamentoString()
    {
        return dataPagamento.format(FORMATTER);
    }

    /** @return id del pagamento */
    public int getId()
    {
        return id;
    }

    /** @param id nuovo identificativo del pagamento */
    public void setId(int id)
    {
        this.id = id;
    }

    /** @return metodo di pagamento */
    public String getMetodo()
    {
        return metodo;
    }

    /** @param metodo nuovo metodo di pagamento */
    public void setMetodo(String metodo)
    {
        this.metodo = metodo;
    }

    /** @return importo pagato */
    public double getImporto()
    {
        return importo;
    }

    /** @param importo nuovo importo pagato */
    public void setImporto(double importo)
    {
        this.importo = importo;
    }

    /** @return data del pagamento */
    public LocalDate getDataPagamento()
    {
        return dataPagamento;
    }

    /** @param dataPagamento nuova data del pagamento */
    public void setDataPagamento(LocalDate dataPagamento)
    {
        this.dataPagamento = dataPagamento;
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

    /**
     * Restituisce una rappresentazione testuale del pagamento,
     * utile per la visualizzazione nelle interfacce grafiche.
     *
     * @return stringa contenente id, metodo, importo e data
     */
    @Override
    public String toString()
    {
        return id + " - " + metodo + " - " + importo + "€ - " + dataPagamento;
    }
}
