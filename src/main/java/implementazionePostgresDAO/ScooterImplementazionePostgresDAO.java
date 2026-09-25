package implementazionePostgresDAO;

import dao.ScooterDAO;
import database.ConnessioneDatabase;
import model.Scooter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link ScooterDAO} per database PostgreSQL.
 * Gestisce tutte le operazioni CRUD (lettura, inserimento, modifica, eliminazione)
 * sulla tabella degli scooter.
 *
 * <p>Utilizza la connessione fornita da {@link ConnessioneDatabase}.</p>
 *
 * @see dao.ScooterDAO
 * @see model.Scooter
 * @see database.ConnessioneDatabase
 *
 * @author Francesco & Vincenzo
 */
public class ScooterImplementazionePostgresDAO implements ScooterDAO {

    /** Connessione al database PostgreSQL. */
    private Connection connection;

    /**
     * Costruttore: ottiene la connessione al database tramite il Singleton.
     */
    public ScooterImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce tutti gli scooter presenti nel database.
     *
     * @return lista di oggetti Scooter
     */
    @Override
    public List<Scooter> getAll() {
        List<Scooter> lista = new ArrayList<>();
        String sql = "SELECT * FROM scooter";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String stato = rs.getBoolean("disponibile")
                        ? "DISPONIBILE"
                        : "NON DISPONIBILE";

                lista.add(new Scooter(
                        rs.getInt("id_scooter"),
                        rs.getString("targa"),
                        rs.getString("marca"),
                        rs.getString("modello"),
                        rs.getInt("cilindrata"),
                        stato
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Restituisce uno scooter tramite il suo ID.
     *
     * @param id identificativo dello scooter
     * @return oggetto Scooter oppure null se non trovato
     */
    @Override
    public Scooter getById(int id) {
        String sql = "SELECT * FROM scooter WHERE id_scooter = ?";
        Scooter scooter = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String stato = rs.getBoolean("disponibile")
                        ? "DISPONIBILE"
                        : "NON DISPONIBILE";

                scooter = new Scooter(
                        rs.getInt("id_scooter"),
                        rs.getString("targa"),
                        rs.getString("marca"),
                        rs.getString("modello"),
                        rs.getInt("cilindrata"),
                        stato
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scooter;
    }

    /**
     * Elimina uno scooter dal database tramite il suo ID.
     *
     * @param id identificativo dello scooter da eliminare
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM scooter WHERE id_scooter = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia lo stato dello scooter invertendo il valore del campo booleano "disponibile".
     *
     * @param id identificativo dello scooter
     */
    @Override
    public void toggleState(int id) {
        String sql = "UPDATE scooter SET disponibile = NOT disponibile WHERE id_scooter = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserisce un nuovo scooter nel database.
     * (Da completare in base alla struttura della tabella scooter)
     *
     * @param scooter oggetto Scooter da inserire
     */
    @Override
    public void insert(Scooter scooter) {
        // Da implementare
    }

    /**
     * Aggiorna i dati di uno scooter esistente.
     * (Da completare in base alla struttura della tabella scooter)
     *
     * @param scooter oggetto Scooter con i nuovi dati
     */
    @Override
    public void update(Scooter scooter) {
        // Da implementare
    }
}
