package dao;

import model.Noleggio;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni di accesso ai dati
 * per la gestione dei noleggi nel sistema.
 *
 * <p>Contiene i metodi principali per leggere, inserire,
 * modificare ed eliminare noleggi dal database.</p>
 *
 * <p>Le classi che implementano questa interfaccia si occupano
 * della comunicazione con il database.</p>
 *
 * @see model.Noleggio
 *
 * @author Francesco & Vincenzo
 */
public interface NoleggioDAO
{

    /**
     * Restituisce la lista completa dei noleggi presenti nel database.
     *
     * @return lista di oggetti Noleggio
     */
    List<Noleggio> getAll();

    /**
     * Restituisce un singolo noleggio tramite il suo identificativo.
     *
     * @param id identificativo del noleggio
     * @return oggetto Noleggio corrispondente, oppure null se non trovato
     */
    Noleggio getById(int id);

    /**
     * Inserisce un nuovo noleggio nel database.
     *
     * @param noleggio oggetto Noleggio da inserire
     */
    void insert(Noleggio noleggio);

    /**
     * Aggiorna i dati di un noleggio già presente nel database.
     *
     * @param noleggio oggetto Noleggio con i nuovi dati
     */
    void update(Noleggio noleggio);

    /**
     * Elimina un noleggio dal database tramite il suo identificativo.
     *
     * @param id identificativo del noleggio da eliminare
     */
    void delete(int id);
}
