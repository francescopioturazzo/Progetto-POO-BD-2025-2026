package implementazionePostgresDAO;

import dao.PagamentoDAO;
import database.ConnessioneDatabase;
import model.Pagamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link PagamentoDAO} per database PostgreSQL.
 * Gestisce tutte le operazioni CRUD (lettura, inserimento, modifica, eliminazione)
 * sulla tabella dei pagamenti.
 *
 * <p>Utilizza la connessione fornita da {@link ConnessioneDatabase}.</p>
 *
 * @see dao.PagamentoDAO
 * @see model.Pagamento
 * @see database.ConnessioneDatabase
 *
 * @author Francesco & Vincenzo
 */
public class PagamentoImplementazionePostgresDAO implements PagamentoDAO {

    /** Connessione al database PostgreSQL. */
    private Connection connection;

    /**
     * Costruttore: ottiene la connessione al database tramite il Singleton.
     */
    public PagamentoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce tutti i pagamenti presenti nel database.
     *
     * @return lista di oggetti Pagamento
     */
    @Override
    public List<Pagamento> getAll() {
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                LocalDate data = null;
                if (rs.getDate("data_pagamento") != null) {
                    data = rs.getDate("data_pagamento").toLocalDate();
                }

                lista.add(new Pagamento(
                        rs.getInt("id_pagamento"),
                        rs.getString("metodo"),
                        rs.getDouble("importo"),
                        data
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Restituisce un singolo pagamento tramite il suo ID.
     *
     * @param id identificativo del pagamento
     * @return oggetto Pagamento oppure null se non trovato
     */
    @Override
    public Pagamento getById(int id) {
        String sql = "SELECT * FROM pagamento WHERE id_pagamento = ?";
        Pagamento pagamento = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LocalDate data = null;
                if (rs.getDate("data_pagamento") != null) {
                    data = rs.getDate("data_pagamento").toLocalDate();
                }

                pagamento = new Pagamento(
                        rs.getInt("id_pagamento"),
                        rs.getString("metodo"),
                        rs.getDouble("importo"),
                        data
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamento;
    }

    /**
     * Inserisce un nuovo pagamento nel database.
     *
     * @param pagamento oggetto Pagamento da inserire
     */
    @Override
    public void insert(Pagamento pagamento) {
        String sql = "INSERT INTO pagamento (metodo, importo, data_pagamento) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pagamento.getMetodo());
            ps.setDouble(2, pagamento.getImporto());

            if (pagamento.getDataPagamento() != null) {
                ps.setDate(3, Date.valueOf(pagamento.getDataPagamento()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna i dati di un pagamento esistente.
     *
     * @param pagamento oggetto Pagamento con i nuovi dati
     */
    @Override
    public void update(Pagamento pagamento) {
        String sql = "UPDATE pagamento SET metodo = ?, importo = ?, data_pagamento = ? WHERE id_pagamento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pagamento.getMetodo());
            ps.setDouble(2, pagamento.getImporto());

            if (pagamento.getDataPagamento() != null) {
                ps.setDate(3, Date.valueOf(pagamento.getDataPagamento()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setInt(4, pagamento.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un pagamento dal database tramite il suo ID.
     *
     * @param id identificativo del pagamento da eliminare
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pagamento WHERE id_pagamento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
