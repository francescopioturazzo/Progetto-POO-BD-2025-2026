package model;
import model.*;
/**
 * The type Utente.
 */
public class Cliente {
    private String password;
    private int idCliente;
    private String nome;
    private String cognome;
    private String patente;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String nuovaPassword) {
        this.password = nuovaPassword;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int nuovoIdCliente) {
        this.idCliente = nuovoIdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nuovoNome) {
        this.nome = nuovoNome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String nuovoCognome) {
        this.cognome = nuovoCognome;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String nuovaPatente) {
        this.patente = nuovaPatente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nuovaEmail) {
        this.email = nuovaEmail;
    }

}
