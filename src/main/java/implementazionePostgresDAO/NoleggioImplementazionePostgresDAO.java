package implementazionePostgresDAO;

import dao.NoleggioDAO;
import database.ConnessioneDatabase;
import model.Noleggio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link NoleggioDAO} per database PostgreSQL.
 * Gestisce tutte le operazioni CRUD (lettura, inserimento, modifica, eliminazione)
 * sulla tabella dei noleggi.
 *
 * <p>Utilizza la connessione fornita da {@link ConnessioneDatabase}.</p>
 *
 * @see dao.NoleggioDAO
 * @see model.Noleggio
 * @see database.ConnessioneDatabase
 *
 * @author Francesco & Vincenzo
 */
public class NoleggioImplementazionePostgresDAO implements NoleggioDAO {

    /** Connessione al database PostgreSQL. */
    private Connection connection;

    /**
     * Costruttore: ottiene la connessione al database tramite il Singleton.
     */
    public NoleggioImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce tutti i noleggi presenti nel database.
     *
     * @return lista di oggetti Noleggio
     */
    @Override
    public List<Noleggio> getAll() {
        List<Noleggio> lista = new ArrayList<>();
        String sql = "SELECT * FROM noleggio ORDER BY id_noleggio";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                LocalDate dataFine = null;
                if (rs.getDate("data_fine") != null) {
                    dataFine = rs.getDate("data_fine").toLocalDate();
                }

                lista.add(new Noleggio(
                        rs.getInt("id_noleggio"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_veicolo"),
                        rs.getString("tipo_veicolo"),
                        rs.getDate("data_inizio").toLocalDate(),
                        dataFine,
                        rs.getDouble("costo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Restituisce un singolo noleggio tramite il suo ID.
     *
     * @param id identificativo del noleggio
     * @return oggetto Noleggio oppure null se non trovato
     */
    @Override
    public Noleggio getById(int id) {
        String sql = "SELECT * FROM noleggio WHERE id_noleggio = ?";
        Noleggio noleggio = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LocalDate dataFine = null;
                if (rs.getDate("data_fine") != null) {
                    dataFine = rs.getDate("data_fine").toLocalDate();
                }

                noleggio = new Noleggio(
                        rs.getInt("id_noleggio"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_veicolo"),
                        rs.getString("tipo_veicolo"),
                        rs.getDate("data_inizio").toLocalDate(),
                        dataFine,
                        rs.getDouble("costo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noleggio;
    }

    /**
     * Inserisce un nuovo noleggio nel database.
     * Prima verifica che il veicolo non sia già noleggiato (data_fine NULL).
     *
     * @param noleggio oggetto Noleggio da inserire
     */
    @Override
    public void insert(Noleggio noleggio) {

        String checkSql = "SELECT COUNT(*) FROM noleggio WHERE id_veicolo = ? AND data_fine IS NULL";

        try (PreparedStatement psCheck = connection.prepareStatement(checkSql)) {
            psCheck.setInt(1, noleggio.getIdVeicolo());
            ResultSet rs = psCheck.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                throw new IllegalStateException("Impossibile noleggiare: il veicolo è già noleggiato.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO noleggio (id_cliente, id_veicolo, tipo_veicolo, data_inizio, data_fine, costo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, noleggio.getIdCliente());
            ps.setInt(2, noleggio.getIdVeicolo());
            ps.setString(3, noleggio.getTipoVeicolo());
            ps.setDate(4, Date.valueOf(noleggio.getDataInizio()));

            if (noleggio.getDataFine() != null) {
                ps.setDate(5, Date.valueOf(noleggio.getDataFine()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setDouble(6, noleggio.getCosto());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna i dati di un noleggio esistente.
     *
     * @param noleggio oggetto Noleggio con i nuovi dati
     */
    @Override
    public void update(Noleggio noleggio) {
        String sql = "UPDATE noleggio SET id_cliente = ?, id_veicolo = ?, tipo_veicolo = ?, data_inizio = ?, data_fine = ?, costo = ? WHERE id_noleggio = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, noleggio.getIdCliente());
            ps.setInt(2, noleggio.getIdVeicolo());
            ps.setString(3, noleggio.getTipoVeicolo());
            ps.setDate(4, Date.valueOf(noleggio.getDataInizio()));

            if (noleggio.getDataFine() != null) {
                ps.setDate(5, Date.valueOf(noleggio.getDataFine()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setDouble(6, noleggio.getCosto());
            ps.setInt(7, noleggio.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un noleggio dal database tramite il suo ID.
     *
     * @param id identificativo del noleggio da eliminare
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM noleggio WHERE id_noleggio = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
