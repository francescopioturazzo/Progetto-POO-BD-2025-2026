package model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Noleggio {

    private final int idNoleggio;
    private Cliente cliente;
    private Veicolo veicolo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double prezzoTotale;


    public Noleggio(int id, Cliente cliente, Veicolo veicolo, LocalDate inizio, LocalDate fine, double prezzo) {

        this.idNoleggio = id;
        this.cliente = cliente;
        this.veicolo = veicolo;
        this.dataInizio = inizio;
        this.dataFine = fine;
        this.prezzoTotale = prezzo;

    }

    public int getIdNoleggio() {
        return idNoleggio;
    }

    public Cliente getCliente() {
        return cliente;
    }

//    public void setCliente(String cliente) {
//        this.cliente = cliente;
//
//    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

//    public void setVeicolo(String veicolo) {
//        this.veicolo = veicolo;
//
//    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;

    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public int calcolaGiorni(LocalDate inizio, LocalDate fine) {

        return 0;
    }

    @Override
    public String toString() {
        return idNoleggio + " - " + cliente + " - " + veicolo;
    }


}
