package implementazionePostgresDAO;

import dao.AutoDAO;
import database.ConnessioneDatabase;
import model.Auto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link AutoDAO} per database PostgreSQL.
 * Gestisce tutte le operazioni CRUD (lettura, inserimento, modifica, eliminazione)
 * sulla tabella delle automobili.
 *
 * <p>Utilizza la connessione fornita da {@link ConnessioneDatabase}.</p>
 *
 * @see dao.AutoDAO
 * @see model.Auto
 * @see database.ConnessioneDatabase
 *
 * @author Francesco & Vincenzo
 */
public class AutoImplementazionePostgresDAO implements AutoDAO {

    /** Connessione al database PostgreSQL. */
    private Connection connection;

    /**
     * Costruttore: ottiene la connessione al database tramite il Singleton.
     */
    public AutoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce tutte le auto presenti nel database.
     *
     * @return lista di oggetti Auto
     */
    @Override
    public List<Auto> getAll() {
        List<Auto> lista = new ArrayList<>();
        String sql = "SELECT * FROM auto ORDER BY id_auto";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Auto(
                        rs.getInt("id_auto"),
                        rs.getString("targa"),
                        rs.getString("marca"),
                        rs.getString("modello"),
                        rs.getInt("porte"),
                        rs.getString("stato")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Restituisce una singola auto tramite il suo ID.
     *
     * @param id identificativo dell'auto
     * @return oggetto Auto oppure null se non trovato
     */
    @Override
    public Auto getById(int id) {
        String sql = "SELECT * FROM auto WHERE id_auto = ?";
        Auto auto = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                auto = new Auto(
                        rs.getInt("id_auto"),
                        rs.getString("targa"),
                        rs.getString("marca"),
                        rs.getString("modello"),
                        rs.getInt("porte"),
                        rs.getString("stato")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auto;
    }

    /**
     * Elimina un'auto dal database tramite il suo ID.
     *
     * @param id identificativo dell'auto da eliminare
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM auto WHERE id_auto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia lo stato dell'auto seguendo una rotazione:
     * DISPONIBILE → NOLEGGIATO → IN_MANUTENZIONE → DISPONIBILE.
     *
     * @param id identificativo dell'auto
     */
    @Override
    public void toggleState(int id) {
        String sqlGet = "SELECT stato FROM auto WHERE id_auto = ?";
        String sqlUpdate = "UPDATE auto SET stato = ? WHERE id_auto = ?";

        try (PreparedStatement psGet = connection.prepareStatement(sqlGet)) {
            psGet.setInt(1, id);
            ResultSet rs = psGet.executeQuery();

            if (rs.next()) {
                String stato = rs.getString("stato");
                String nuovo;

                switch (stato.toUpperCase()) {
                    case "DISPONIBILE":
                        nuovo = "NOLEGGIATO";
                        break;
                    case "NOLEGGIATO":
                        nuovo = "IN_MANUTENZIONE";
                        break;
                    default:
                        nuovo = "DISPONIBILE";
                        break;
                }

                try (PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate)) {
                    psUpdate.setString(1, nuovo);
                    psUpdate.setInt(2, id);
                    psUpdate.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserisce una nuova auto nel database.
     *
     * @param auto oggetto Auto da inserire
     */
    @Override
    public void insert(Auto auto) {
        String sql = "INSERT INTO auto (targa, marca, modello, porte, stato) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auto.getTarga());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModello());
            ps.setInt(4, auto.getPorte());
            ps.setString(5, auto.getStato());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna i dati di un'auto esistente.
     *
     * @param auto oggetto Auto con i nuovi dati
     */
    @Override
    public void update(Auto auto) {
        String sql = "UPDATE auto SET targa = ?, marca = ?, modello = ?, porte = ?, stato = ? WHERE id_auto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auto.getTarga());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModello());
            ps.setInt(4, auto.getPorte());
            ps.setString(5, auto.getStato());
            ps.setInt(6, auto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna solo lo stato dell'auto.
     *
     * @param id identificativo dell'auto
     * @param nuovoStato nuovo stato da impostare
     */
    @Override
    public void updateStato(int id, String nuovoStato) {
        String sql = "UPDATE auto SET stato = ? WHERE id_auto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuovoStato);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
