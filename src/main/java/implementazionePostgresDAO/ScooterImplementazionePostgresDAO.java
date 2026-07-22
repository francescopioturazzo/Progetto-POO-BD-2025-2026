package implementazionePostgresDAO;

import dao.ScooterDAO;
import database.ConnessioneDatabase;
import model.Scooter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScooterImplementazionePostgresDAO implements ScooterDAO {

    private Connection connection;

    public ScooterImplementazionePostgresDAO() {
        try {
            // 🔥 CORRETTO
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void insert(Scooter scooter) {
        // qui fai l'INSERT nel DB
    }

    @Override
    public void update(Scooter scooter) {
        // qui fai l'UPDATE nel DB
    }
}
