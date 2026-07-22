package dao;

import model.Auto;
import java.util.List;

public interface AutoDAO {
    List<Auto> getAll();
    Auto getById(int id);
    void delete(int id);
    void toggleState(int id);
    void insert(Auto auto);
    void update(Auto auto);
    void updateStato(int id, String nuovoStato);
}
