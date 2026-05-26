package model;
import model.*;
/**
 * The type Utente.
 */
public class Utente {
    private final String login;
    private String password;
    private int idCliente;
    private String nome;
    private String cognome;
    private String patente;
    private String email;
    Utente c =new Utente("a", "b");


    /**
     * Instantiates a new Utente.
     *
     * @param login    the login
     * @param password the password
     */
    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }
}
