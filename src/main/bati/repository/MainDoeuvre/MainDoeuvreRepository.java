package main.bati.repository.MainDoeuvre;

import main.bati.model.MainDoeuvre;
import java.util.List;

public interface MainDoeuvreRepository {
    void add(MainDoeuvre mainDoeuvre);

    List<MainDoeuvre> findAll();

    MainDoeuvre findById(int id);

    void update(MainDoeuvre mainDoeuvre);

    void delete(int id);
}
