package main.bati.repository.Composant;

import main.bati.model.Composant;
import java.util.List;

public interface ComposantRepository {
    void add(Composant composant);
    Composant findByName(String  nom);
    List<Composant> findAll();
    void update(Composant composant);
    void delete(int id);
}
