package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Noleggio {

    private int id;
    private int idCliente;
    private int idVeicolo;
    private String tipoVeicolo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double costo;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Noleggio() {}

    public Noleggio(int id, int idCliente, int idVeicolo, String tipoVeicolo,
                    LocalDate dataInizio, LocalDate dataFine, double costo) {

        this.id = id;
        this.idCliente = idCliente;
        this.idVeicolo = idVeicolo;
        this.tipoVeicolo = tipoVeicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costo = costo;
    }

    public Noleggio(int id, int idCliente, int idVeicolo,
                    String dataInizioString, String dataFineString, double costo) {

        this.id = id;
        this.idCliente = idCliente;
        this.idVeicolo = idVeicolo;
        this.tipoVeicolo = "";

        this.dataInizio = LocalDate.parse(dataInizioString, FORMATTER);

        if (dataFineString == null || dataFineString.isEmpty()) {
            this.dataFine = null;
        } else {
            this.dataFine = LocalDate.parse(dataFineString, FORMATTER);
        }

        this.costo = costo;
    }

    public String getDataInizioString() {
        return dataInizio.format(FORMATTER);
    }

    public String getDataFineString() {
        if (dataFine == null) {
            return "—";
        }
        return dataFine.format(FORMATTER);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdVeicolo() { return idVeicolo; }
    public void setIdVeicolo(int idVeicolo) { this.idVeicolo = idVeicolo; }

    public String getTipoVeicolo() { return tipoVeicolo; }
    public void setTipoVeicolo(String tipoVeicolo) { this.tipoVeicolo = tipoVeicolo; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public LocalDate getDataFine() { return dataFine; }
    public void setDataFine(LocalDate dataFine) { this.dataFine = dataFine; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    @Override
    public String toString() {
        return id + " - Cliente: " + idCliente +
                " - Veicolo: " + idVeicolo +
                " (" + tipoVeicolo + ")" +
                " - " + getDataInizioString() + " → " + getDataFineString() +
                " - " + costo + "€";
    }
}
