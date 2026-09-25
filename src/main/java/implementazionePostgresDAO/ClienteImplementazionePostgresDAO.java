package implementazionePostgresDAO;

import dao.ClienteDAO;
import database.ConnessioneDatabase;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link ClienteDAO} per database PostgreSQL.
 * Gestisce tutte le operazioni CRUD (lettura, inserimento, modifica, eliminazione)
 * sulla tabella dei clienti.
 *
 * <p>Utilizza la connessione fornita da {@link ConnessioneDatabase}.</p>
 *
 * @see dao.ClienteDAO
 * @see model.Cliente
 * @see database.ConnessioneDatabase
 *
 * @author Francesco & Vincenzo
 */
public class ClienteImplementazionePostgresDAO implements ClienteDAO {

    /** Connessione al database PostgreSQL. */
    private Connection connection;

    /**
     * Costruttore: ottiene la connessione al database tramite il Singleton.
     */
    public ClienteImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce tutti i clienti presenti nel database.
     *
     * @return lista di oggetti Cliente
     */
    @Override
    public List<Cliente> getAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("telefono"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Restituisce un singolo cliente tramite il suo ID.
     *
     * @param id identificativo del cliente
     * @return oggetto Cliente oppure null se non trovato
     */
    @Override
    public Cliente getById(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    /**
     * Inserisce un nuovo cliente nel database.
     *
     * @param cliente oggetto Cliente da inserire
     */
    @Override
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cognome, telefono, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCognome());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna i dati di un cliente esistente.
     *
     * @param cliente oggetto Cliente con i nuovi dati
     */
    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cognome = ?, telefono = ?, email = ? WHERE id_cliente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCognome());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un cliente dal database tramite il suo ID.
     *
     * @param id identificativo del cliente da eliminare
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
