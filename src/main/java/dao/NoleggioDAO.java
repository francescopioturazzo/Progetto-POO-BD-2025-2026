package dao;

import model.Noleggio;
import java.util.List;

public interface NoleggioDAO {

    List<Noleggio> getAll();
    Noleggio getById(int id);
    void insert(Noleggio noleggio);
    void update(Noleggio noleggio);
    void delete(int id);
}
