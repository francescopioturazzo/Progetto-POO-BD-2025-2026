package dao;

import model.Pagamento;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni di accesso ai dati
 * per la gestione dei pagamenti nel sistema di noleggio.
 *
 * <p>Contiene i metodi principali per leggere, inserire,
 * modificare ed eliminare pagamenti dal database.</p>
 *
 * <p>Le classi che implementano questa interfaccia si occupano
 * della comunicazione con il database.</p>
 *
 * @see model.Pagamento
 *
 * @author Francesco & Vincenzo
 */
public interface PagamentoDAO
{

    /**
     * Restituisce la lista completa dei pagamenti presenti nel database.
     *
     * @return lista di oggetti Pagamento
     */
    List<Pagamento> getAll();

    /**
     * Restituisce un singolo pagamento tramite il suo identificativo.
     *
     * @param id identificativo del pagamento
     * @return oggetto Pagamento corrispondente, oppure null se non trovato
     */
    Pagamento getById(int id);

    /**
     * Inserisce un nuovo pagamento nel database.
     *
     * @param pagamento oggetto Pagamento da inserire
     */
    void insert(Pagamento pagamento);

    /**
     * Aggiorna i dati di un pagamento già presente nel database.
     *
     * @param pagamento oggetto Pagamento con i nuovi dati
     */
    void update(Pagamento pagamento);

    /**
     * Elimina un pagamento dal database tramite il suo identificativo.
     *
     * @param id identificativo del pagamento da eliminare
     */
    void delete(int id);
}
