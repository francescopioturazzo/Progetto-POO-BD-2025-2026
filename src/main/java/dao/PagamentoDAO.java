package dao;

import model.Pagamento;
import java.util.List;

public interface PagamentoDAO {

    List<Pagamento> getAll();
    Pagamento getById(int id);
    void insert(Pagamento pagamento);
    void update(Pagamento pagamento);
    void delete(int id);
}