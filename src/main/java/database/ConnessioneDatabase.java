package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Gestisce la connessione al database PostgreSQL utilizzato dal sistema di noleggio.
 *
 * <p>Questa classe utilizza il pattern Singleton: significa che esiste
 * una sola connessione condivisa in tutta l'applicazione.</p>
 *
 * <p>La connessione viene aperta una sola volta e poi riutilizzata
 * da tutte le classi che devono comunicare con il database.</p>
 *
 * <p>La classe fornisce anche un semplice metodo di test nel main
 * per verificare che la connessione funzioni correttamente.</p>
 *
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 */
public class ConnessioneDatabase
{

	/** URL del database PostgreSQL. */
	private static final String URL = "jdbc:postgresql://localhost:5432/noleggio";

	/** Nome utente del database. */
	private static final String USER = "postgres";

	/** Password del database. */
	private static final String PASSWORD = "admin123";

	/** Driver JDBC di PostgreSQL. */
	private static final String DRIVER = "org.postgresql.Driver";

	/** Istanza Singleton della connessione. */
	private static ConnessioneDatabase instance;

	/** Oggetto Connection utilizzato per comunicare con il database. */
	private Connection connection;

	/**
	 * Costruttore privato: impedisce la creazione di più connessioni.
	 * Carica il driver e apre la connessione al database.
	 *
	 * @throws SQLException se la connessione non può essere stabilita
	 */
	private ConnessioneDatabase() throws SQLException
	{
		try
		{
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connessione riuscita al database!");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Driver PostgreSQL non trovato: " + e.getMessage());
		}
	}

	/**
	 * Restituisce l'unica istanza della connessione (Singleton).
	 * Se la connessione è chiusa, ne crea una nuova.
	 *
	 * @return istanza di ConnessioneDatabase
	 * @throws SQLException se la connessione non può essere aperta
	 */
	public static ConnessioneDatabase getInstance() throws SQLException
	{
		if (instance == null || instance.connection.isClosed())
		{
			instance = new ConnessioneDatabase();
		}
		return instance;
	}

	/**
	 * Restituisce l'oggetto Connection da utilizzare per le query.
	 *
	 * @return connessione al database
	 */
	public Connection getConnection()
	{
		return connection;
	}

	/**
	 * Metodo di test che esegue una semplice query sul database.
	 * Utile per verificare che la connessione funzioni correttamente.
	 *
	 * @param args argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		try
		{
			Connection conn = ConnessioneDatabase.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente LIMIT 10");

			System.out.println("Clienti nel database:");
			while (rs.next())
			{
				System.out.println(rs.getInt("id_cliente") + " - " +
						rs.getString("nome") + " " +
						rs.getString("cognome"));
			}

			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
