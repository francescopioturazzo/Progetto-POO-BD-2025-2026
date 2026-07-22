package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {

    List<Cliente> getAll();
    Cliente getById(int id);
    void insert(Cliente cliente);
    void update(Cliente cliente);
    void delete(int id);
}
