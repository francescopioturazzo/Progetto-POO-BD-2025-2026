package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnessioneDatabase {

	// 🔧 Parametri di connessione
	private static final String URL = "jdbc:postgresql://localhost:5432/noleggio";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin123";
	private static final String DRIVER = "org.postgresql.Driver";

	// 🔒 Singleton
	private static ConnessioneDatabase instance;
	private Connection connection;

	// 🧱 Costruttore privato
	private ConnessioneDatabase() throws SQLException {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("✅ Connessione riuscita al database!");
		} catch (ClassNotFoundException e) {
			System.err.println("❌ Driver PostgreSQL non trovato: " + e.getMessage());
		}
	}

	// 🔁 Metodo per ottenere l'istanza
	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null || instance.connection.isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}

	// 🔓 Metodo per ottenere la connessione
	public Connection getConnection() {
		return connection;
	}

	// 🧪 Metodo di test
	public static void main(String[] args) {
		try {
			Connection conn = ConnessioneDatabase.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente LIMIT 10");

			System.out.println("📋 Clienti nel database:");
			while (rs.next()) {
				System.out.println(rs.getInt("id_cliente") + " - " +
						rs.getString("nome") + " " +
						rs.getString("cognome"));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
