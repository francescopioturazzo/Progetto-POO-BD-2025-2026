package model;

public class Pagamento {

    private int idPagamento;
    private String metodo;
    private double importo;
    private String data;

    public Pagamento() {
        // costruttore vuoto
    }

    public Pagamento(int idPagamento, String metodo, double importo, String data) {
        this.idPagamento = idPagamento;
        this.metodo = metodo;
        this.importo = importo;
        this.data = data;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int nuovoIdPagamento) {
        this.idPagamento = nuovoIdPagamento;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String nuovoMetodoPagamento) {
        this.metodo = nuovoMetodoPagamento;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double nuovoImportoPagamento) {
        this.importo = nuovoImportoPagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String nuovaDataPagamento) {
        this.data = nuovaDataPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "idPagamento=" + idPagamento + ", metodo='" + metodo + '\'' + ", importo=" + importo + ", data='" + data + '\'' + '}';
    }
}

