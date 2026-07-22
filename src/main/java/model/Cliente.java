package model;

public class Cliente {

    private int id;
    private String nome;
    private String cognome;
    private String patente;
    private String email;
    private String telefono;
    private String password;

    public Cliente() {}

    public Cliente(int id, String nome, String cognome,
                   String patente, String email,
                   String telefono, String password) {

        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.patente = patente;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    // 🔥 Costruttore compatibile con il database (senza patente e password)
    public Cliente(int id, String nome, String cognome, String telefono, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
    }

    // 🔥 Costruttore per INSERT (senza ID)
    public Cliente(String nome, String cognome, String telefono, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
    }


    // Getter e Setter compatibili con GUI e DAO
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validazioni
    public boolean emailValida() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean telefonoValido() {
        return telefono != null && telefono.matches("\\d{10}");
    }

    public boolean passwordValida() {
        return password != null && password.length() >= 6;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " " + cognome + " (" + email + ")";
    }
}
