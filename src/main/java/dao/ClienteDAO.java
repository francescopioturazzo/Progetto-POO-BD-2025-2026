package dao;

import model.Cliente;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni di accesso ai dati
 * per la gestione dei clienti nel sistema di noleggio.
 *
 * <p>Contiene i metodi principali per leggere, inserire,
 * modificare ed eliminare clienti dal database.</p>
 *
 * <p>Le classi che implementano questa interfaccia si occupano
 * della comunicazione con il database.</p>
 *
 * @see model.Cliente
 *
 * @author Francesco & Vincenzo
 */
public interface ClienteDAO
{

    /**
     * Restituisce la lista completa dei clienti presenti nel database.
     *
     * @return lista di oggetti Cliente
     */
    List<Cliente> getAll();

    /**
     * Restituisce un singolo cliente tramite il suo identificativo.
     *
     * @param id identificativo del cliente
     * @return oggetto Cliente corrispondente, oppure null se non trovato
     */
    Cliente getById(int id);

    /**
     * Inserisce un nuovo cliente nel database.
     *
     * @param cliente oggetto Cliente da inserire
     */
    void insert(Cliente cliente);

    /**
     * Aggiorna i dati di un cliente già presente nel database.
     *
     * @param cliente oggetto Cliente con i nuovi dati
     */
    void update(Cliente cliente);

    /**
     * Elimina un cliente dal database tramite il suo identificativo.
     *
     * @param id identificativo del cliente da eliminare
     */
    void delete(int id);
}
