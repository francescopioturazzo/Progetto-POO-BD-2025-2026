package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagamento {

    private int id;
    private String metodo;
    private double importo;
    private LocalDate dataPagamento;
    private int idCliente; // 🔥 AGGIUNTO per compatibilità con GUI

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Pagamento() {}

    // 🔥 Costruttore usato dal DAO (LocalDate)
    public Pagamento(int id, String metodo, double importo, LocalDate dataPagamento) {
        this.id = id;
        this.metodo = metodo;
        this.importo = importo;
        this.dataPagamento = dataPagamento;
    }

    // 🔥 Costruttore compatibile con GUI (String → LocalDate)
    public Pagamento(int id, double importo, String dataPagamentoString, String metodo, int idCliente) {
        this.id = id;
        this.importo = importo;
        this.metodo = metodo;
        this.idCliente = idCliente;
        this.dataPagamento = LocalDate.parse(dataPagamentoString, FORMATTER);
    }

    // 🔥 Getter per GUI (LocalDate → String)
    public String getDataPagamentoString() {
        return dataPagamento.format(FORMATTER);
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }

    public double getImporto() { return importo; }
    public void setImporto(double importo) { this.importo = importo; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    @Override
    public String toString() {
        return id + " - " + metodo + " - " + importo + "€ - " + dataPagamento;
    }
}
