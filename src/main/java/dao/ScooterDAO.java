package dao;

import model.Scooter;
import java.util.List;

public interface ScooterDAO {

    List<Scooter> getAll();
    Scooter getById(int id);
    void delete(int id);
    void toggleState(int id);
    void insert(Scooter scooter);
    void update(Scooter scooter);
}
