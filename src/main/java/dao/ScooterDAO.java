package dao;

import model.Scooter;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni di accesso ai dati
 * per la gestione degli scooter nel sistema di noleggio.
 *
 * <p>Contiene i metodi principali per leggere, inserire,
 * modificare ed eliminare scooter dal database.</p>
 *
 * <p>Le classi che implementano questa interfaccia si occupano
 * della comunicazione con il database.</p>
 *
 * @see model.Scooter
 *
 * @author Francesco & Vincenzo
 */
public interface ScooterDAO
{

    /**
     * Restituisce la lista completa degli scooter presenti nel database.
     *
     * @return lista di oggetti Scooter
     */
    List<Scooter> getAll();

    /**
     * Restituisce uno scooter tramite il suo identificativo.
     *
     * @param id identificativo dello scooter
     * @return oggetto Scooter corrispondente, oppure null se non trovato
     */
    Scooter getById(int id);

    /**
     * Elimina uno scooter dal database tramite il suo identificativo.
     *
     * @param id identificativo dello scooter da eliminare
     */
    void delete(int id);

    /**
     * Cambia lo stato dello scooter (es. disponibile → noleggiato).
     *
     * @param id identificativo dello scooter
     */
    void toggleState(int id);

    /**
     * Inserisce un nuovo scooter nel database.
     *
     * @param scooter oggetto Scooter da inserire
     */
    void insert(Scooter scooter);

    /**
     * Aggiorna i dati di uno scooter già presente nel database.
     *
     * @param scooter oggetto Scooter con i nuovi dati
     */
    void update(Scooter scooter);
}
