package dao;

import model.Auto;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni di accesso ai dati
 * per la gestione delle automobili nel sistema di noleggio.
 *
 * <p>Contiene i metodi principali per leggere, inserire,
 * modificare ed eliminare auto dal database.</p>
 *
 * <p>Le classi che implementano questa interfaccia si occupano
 * della comunicazione con il database.</p>
 *
 * @see model.Auto
 *
 *@author Francesco & Vincenzo
 */
public interface AutoDAO
{

    /**
     * Restituisce la lista completa delle auto presenti nel database.
     *
     * @return lista di oggetti Auto
     */
    List<Auto> getAll();

    /**
     * Restituisce una singola auto tramite il suo identificativo.
     *
     * @param id identificativo dell'auto
     * @return oggetto Auto corrispondente, oppure null se non trovato
     */
    Auto getById(int id);

    /**
     * Elimina un'auto dal database tramite il suo identificativo.
     *
     * @param id identificativo dell'auto da eliminare
     */
    void delete(int id);

    /**
     * Cambia lo stato dell'auto (es. disponibile → noleggiata).
     *
     * @param id identificativo dell'auto
     */
    void toggleState(int id);

    /**
     * Inserisce una nuova auto nel database.
     *
     * @param auto oggetto Auto da inserire
     */
    void insert(Auto auto);

    /**
     * Aggiorna i dati di un'auto già presente nel database.
     *
     * @param auto oggetto Auto con i nuovi dati
     */
    void update(Auto auto);

    /**
     * Aggiorna solo lo stato dell'auto.
     *
     * @param id identificativo dell'auto
     * @param nuovoStato nuovo stato da impostare
     */
    void updateStato(int id, String nuovoStato);
}
