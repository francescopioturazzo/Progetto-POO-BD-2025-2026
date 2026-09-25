package model;

/**
 * Rappresenta un cliente registrato nel sistema di noleggio.
 * La classe contiene le informazioni anagrafiche e di contatto
 * necessarie per identificare il cliente e gestire le operazioni di noleggio.
 *
 * <p>Include semplici metodi di validazione per email, telefono
 * e password, utili per controlli preliminari lato applicazione.</p>
 *
 * <p>Questa classe funge da modello dati utilizzato da GUI, controller e DAO.</p>
 *
 * @see model.Noleggio
 *
 * @author Francesco & Vincenzo
 */
public class Cliente
{

    /** Identificativo univoco del cliente nel database. */
    private int id;

    /** Nome del cliente. */
    private String nome;

    /** Cognome del cliente. */
    private String cognome;

    /** Numero di patente del cliente. */
    private String patente;

    /** Indirizzo email del cliente. */
    private String email;

    /** Numero di telefono del cliente. */
    private String telefono;

    /** Password del cliente per l'accesso al sistema. */
    private String password;

    /**
     * Costruttore vuoto, utile per inizializzazioni progressive.
     */
    public Cliente()
    {
    }

    /**
     * Costruttore completo con tutti gli attributi.
     *
     * @param id identificativo del cliente
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param patente numero di patente
     * @param email indirizzo email
     * @param telefono numero di telefono
     * @param password password di accesso
     */
    public Cliente(int id, String nome, String cognome,
                   String patente, String email,
                   String telefono, String password)
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.patente = patente;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    /**
     * Costruttore compatibile con il database quando non vengono gestiti
     * patente e password.
     *
     * @param id identificativo del cliente
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param telefono numero di telefono
     * @param email indirizzo email
     */
    public Cliente(int id, String nome, String cognome, String telefono, String email)
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Costruttore utilizzato per l'inserimento di un nuovo cliente
     * (senza ID, generato dal database).
     *
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param telefono numero di telefono
     * @param email indirizzo email
     */
    public Cliente(String nome, String cognome, String telefono, String email)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
    }

    /** @return id del cliente */
    public int getId()
    {
        return id;
    }

    /** @param id nuovo identificativo del cliente */
    public void setId(int id)
    {
        this.id = id;
    }

    /** @return nome del cliente */
    public String getNome()
    {
        return nome;
    }

    /** @param nome nuovo nome del cliente */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /** @return cognome del cliente */
    public String getCognome()
    {
        return cognome;
    }

    /** @param cognome nuovo cognome del cliente */
    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }

    /** @return numero di patente del cliente */
    public String getPatente()
    {
        return patente;
    }

    /** @param patente nuova patente del cliente */
    public void setPatente(String patente)
    {
        this.patente = patente;
    }

    /** @return email del cliente */
    public String getEmail()
    {
        return email;
    }

    /** @param email nuova email del cliente */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /** @return numero di telefono del cliente */
    public String getTelefono()
    {
        return telefono;
    }

    /** @param telefono nuovo numero di telefono del cliente */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    /** @return password del cliente */
    public String getPassword()
    {
        return password;
    }

    /** @param password nuova password del cliente */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Verifica se l'email è formalmente valida.
     *
     * @return true se contiene '@' e '.', false altrimenti
     */
    public boolean emailValida()
    {
        return email != null && email.contains("@") && email.contains(".");
    }

    /**
     * Verifica se il numero di telefono è composto da 10 cifre.
     *
     * @return true se rispetta il formato, false altrimenti
     */
    public boolean telefonoValido()
    {
        return telefono != null && telefono.matches("\\d{10}");
    }

    /**
     * Verifica se la password rispetta una lunghezza minima.
     *
     * @return true se la password ha almeno 6 caratteri
     */
    public boolean passwordValida()
    {
        return password != null && password.length() >= 6;
    }

    /**
     * Restituisce una rappresentazione testuale del cliente,
     * utile per la visualizzazione nelle interfacce grafiche.
     *
     * @return stringa contenente id, nome, cognome ed email
     */
    @Override
    public String toString()
    {
        return id + " - " + nome + " " + cognome + " (" + email + ")";
    }
}
