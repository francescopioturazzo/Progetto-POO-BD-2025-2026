package controller;

import dao.*;
import implementazionePostgresDAO.*;
import model.*;

import java.util.List;

public class Controller {

    private final AutoDAO autoDAO = new AutoImplementazionePostgresDAO();
    private final ScooterDAO scooterDAO = new ScooterImplementazionePostgresDAO();
    private final ClienteDAO clienteDAO = new ClienteImplementazionePostgresDAO();
    private final NoleggioDAO noleggioDAO = new NoleggioImplementazionePostgresDAO();
    private final PagamentoDAO pagamentoDAO = new PagamentoImplementazionePostgresDAO();

    public List<Auto> getAllAuto() {
        return autoDAO.getAll();
    }

    public Auto getAutoById(int id) {
        return autoDAO.getById(id);
    }

    public void deleteAuto(int id) {
        autoDAO.delete(id);
    }

    public void toggleAutoState(int id) {
        autoDAO.toggleState(id);
    }

    public void insertAuto(Auto auto) {
        autoDAO.insert(auto);
    }

    public void updateAuto(Auto auto) {
        autoDAO.update(auto);
    }

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

    public List<Scooter> getAllScooter() {
        return scooterDAO.getAll();
    }

    public Scooter getScooterById(int id) {
        return scooterDAO.getById(id);
    }

    public void deleteScooter(int id) {
        scooterDAO.delete(id);
    }

    public void toggleScooterState(int id) {
        scooterDAO.toggleState(id);
    }

    public void insertScooter(Scooter scooter) {
        scooterDAO.insert(scooter);
    }

    public void updateScooter(Scooter scooter) {
        scooterDAO.update(scooter);
    }

    public List<Cliente> getAllClienti() {
        return clienteDAO.getAll();
    }

    public Cliente getClienteById(int id) {
        return clienteDAO.getById(id);
    }

    public void insertCliente(Cliente cliente) {
        clienteDAO.insert(cliente);
    }

    public void updateCliente(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public void deleteCliente(int id) {
        clienteDAO.delete(id);
    }

    public List<Noleggio> getAllNoleggi() {
        return noleggioDAO.getAll();
    }

    public Noleggio getNoleggioById(int id) {
        return noleggioDAO.getById(id);
    }

    public void updateNoleggio(Noleggio noleggio) {
        noleggioDAO.update(noleggio);
    }

    public void deleteNoleggio(int id) {
        noleggioDAO.delete(id);
    }

    public List<Pagamento> getAllPagamenti() {
        return pagamentoDAO.getAll();
    }

    public Pagamento getPagamentoById(int id) {
        return pagamentoDAO.getById(id);
    }

    public void insertPagamento(Pagamento pagamento) {
        pagamentoDAO.insert(pagamento);
    }

    public void updatePagamento(Pagamento pagamento) {
        pagamentoDAO.update(pagamento);
    }

    public void deletePagamento(int id) {
        pagamentoDAO.delete(id);
    }
}
