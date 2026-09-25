package controller;

import dao.*;
import implementazionePostgresDAO.*;
import model.*;

import java.util.List;

/**
 * Controller principale dell'applicazione.
 * Coordina tutte le operazioni tra GUI, DAO e modelli.
 *
 * <p>Questa classe funge da punto centrale per la logica del programma:
 * ogni richiesta proveniente dalle interfacce grafiche passa da qui,
 * che a sua volta delega ai vari DAO.</p>
 *
 * @see dao.AutoDAO
 * @see dao.ScooterDAO
 * @see dao.ClienteDAO
 * @see dao.NoleggioDAO
 * @see dao.PagamentoDAO
 */
public class Controller {

    /** DAO per la gestione delle automobili. */
    private final AutoDAO autoDAO = new AutoImplementazionePostgresDAO();

    /** DAO per la gestione degli scooter. */
    private final ScooterDAO scooterDAO = new ScooterImplementazionePostgresDAO();

    /** DAO per la gestione dei clienti. */
    private final ClienteDAO clienteDAO = new ClienteImplementazionePostgresDAO();

    /** DAO per la gestione dei noleggi. */
    private final NoleggioDAO noleggioDAO = new NoleggioImplementazionePostgresDAO();

    /** DAO per la gestione dei pagamenti. */
    private final PagamentoDAO pagamentoDAO = new PagamentoImplementazionePostgresDAO();

    // ---------------------------------------------------------
    //                     SEZIONE AUTO
    // ---------------------------------------------------------

    /**
     * Restituisce tutte le auto presenti nel sistema.
     *
     * @return lista di auto
     */
    public List<Auto> getAllAuto() {
        return autoDAO.getAll();
    }

    /**
     * Restituisce una singola auto tramite ID.
     *
     * @param id identificativo dell'auto
     * @return auto trovata oppure null
     */
    public Auto getAutoById(int id) {
        return autoDAO.getById(id);
    }

    /**
     * Elimina un'auto dal sistema.
     *
     * @param id identificativo dell'auto da eliminare
     */
    public void deleteAuto(int id) {
        autoDAO.delete(id);
    }

    /**
     * Cambia lo stato dell'auto (DISPONIBILE → NOLEGGIATO → IN_MANUTENZIONE).
     *
     * @param id identificativo dell'auto
     */
    public void toggleAutoState(int id) {
        autoDAO.toggleState(id);
    }

    /**
     * Inserisce una nuova auto nel sistema.
     *
     * @param auto oggetto Auto da inserire
     */
    public void insertAuto(Auto auto) {
        autoDAO.insert(auto);
    }

    /**
     * Aggiorna i dati di un'auto esistente.
     *
     * @param auto oggetto Auto con i nuovi dati
     */
    public void updateAuto(Auto auto) {
        autoDAO.update(auto);
    }

    /**
     * Inserisce un nuovo noleggio e aggiorna lo stato del veicolo.
     *
     * @param n oggetto Noleggio da inserire
     * @throws IllegalStateException se il veicolo è già noleggiato
     */
    public void insertNoleggio(Noleggio n) {
        List<Noleggio> lista = noleggioDAO.getAll();
        for (Noleggio noleggio : lista) {
            if (noleggio.getIdVeicolo() == n.getIdVeicolo() && noleggio.getDataFine() == null) {
                throw new IllegalStateException("Impossibile noleggiare: il veicolo è già noleggiato.");
            }
        }
        noleggioDAO.insert(n);
        autoDAO.updateStato(n.getIdVeicolo(), "NOLEGGIATO");
    }

    // ---------------------------------------------------------
    //                     SEZIONE SCOOTER
    // ---------------------------------------------------------

    /**
     * Restituisce tutti gli scooter presenti nel sistema.
     *
     * @return lista di scooter
     */
    public List<Scooter> getAllScooter() {
        return scooterDAO.getAll();
    }

    /**
     * Restituisce uno scooter tramite ID.
     *
     * @param id identificativo dello scooter
     * @return scooter trovato oppure null
     */
    public Scooter getScooterById(int id) {
        return scooterDAO.getById(id);
    }

    /**
     * Elimina uno scooter dal sistema.
     *
     * @param id identificativo dello scooter
     */
    public void deleteScooter(int id) {
        scooterDAO.delete(id);
    }

    /**
     * Cambia lo stato dello scooter (disponibile/non disponibile).
     *
     * @param id identificativo dello scooter
     */
    public void toggleScooterState(int id) {
        scooterDAO.toggleState(id);
    }

    /**
     * Inserisce un nuovo scooter nel sistema.
     *
     * @param scooter oggetto Scooter da inserire
     */
    public void insertScooter(Scooter scooter) {
        scooterDAO.insert(scooter);
    }

    /**
     * Aggiorna i dati di uno scooter esistente.
     *
     * @param scooter oggetto Scooter con i nuovi dati
     */
    public void updateScooter(Scooter scooter) {
        scooterDAO.update(scooter);
    }
    // ---------------------------------------------------------
    //                     SEZIONE CLIENTI
    // ---------------------------------------------------------

    /**
     * Restituisce tutti i clienti presenti nel sistema.
     *
     * @return lista di clienti
     */
    public List<Cliente> getAllClienti() {
        return clienteDAO.getAll();
    }

    /**
     * Restituisce un cliente tramite ID.
     *
     * @param id identificativo del cliente
     * @return cliente trovato oppure null
     */
    public Cliente getClienteById(int id) {
        return clienteDAO.getById(id);
    }

    /**
     * Inserisce un nuovo cliente nel sistema.
     *
     * @param cliente oggetto Cliente da inserire
     */
    public void insertCliente(Cliente cliente) {
        clienteDAO.insert(cliente);
    }

    /**
     * Aggiorna i dati di un cliente esistente.
     *
     * @param cliente oggetto Cliente con i nuovi dati
     */
    public void updateCliente(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    /**
     * Elimina un cliente dal sistema.
     *
     * @param id identificativo del cliente da eliminare
     */
    public void deleteCliente(int id) {
        clienteDAO.delete(id);
    }

    // ---------------------------------------------------------
    //                     SEZIONE NOLEGGI
    // ---------------------------------------------------------

    /**
     * Restituisce tutti i noleggi presenti nel sistema.
     *
     * @return lista di noleggi
     */
    public List<Noleggio> getAllNoleggi() {
        return noleggioDAO.getAll();
    }

    /**
     * Restituisce un noleggio tramite ID.
     *
     * @param id identificativo del noleggio
     * @return noleggio trovato oppure null
     */
    public Noleggio getNoleggioById(int id) {
        return noleggioDAO.getById(id);
    }

    /**
     * Aggiorna i dati di un noleggio esistente.
     *
     * @param noleggio oggetto Noleggio con i nuovi dati
     */
    public void updateNoleggio(Noleggio noleggio) {
        noleggioDAO.update(noleggio);
    }

    /**
     * Elimina un noleggio dal sistema.
     *
     * @param id identificativo del noleggio da eliminare
     */
    public void deleteNoleggio(int id) {
        noleggioDAO.delete(id);
    }

    // ---------------------------------------------------------
    //                     SEZIONE PAGAMENTI
    // ---------------------------------------------------------

    /**
     * Restituisce tutti i pagamenti presenti nel sistema.
     *
     * @return lista di pagamenti
     */
    public List<Pagamento> getAllPagamenti() {
        return pagamentoDAO.getAll();
    }

    /**
     * Restituisce un pagamento tramite ID.
     *
     * @param id identificativo del pagamento
     * @return pagamento trovato oppure null
     */
    public Pagamento getPagamentoById(int id) {
        return pagamentoDAO.getById(id);
    }

    /**
     * Inserisce un nuovo pagamento nel sistema.
     *
     * @param pagamento oggetto Pagamento da inserire
     */
    public void insertPagamento(Pagamento pagamento) {
        pagamentoDAO.insert(pagamento);
    }

    /**
     * Aggiorna i dati di un pagamento esistente.
     *
     * @param pagamento oggetto Pagamento con i nuovi dati
     */
    public void updatePagamento(Pagamento pagamento) {
        pagamentoDAO.update(pagamento);
    }

    /**
     * Elimina un pagamento dal sistema.
     *
     * @param id identificativo del pagamento da eliminare
     */
    public void deletePagamento(int id) {
        pagamentoDAO.delete(id);
    }
}