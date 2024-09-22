package main.bati.repository.MainDoeuvre;

import main.bati.model.MainDoeuvre;

import java.util.List;

public interface MainDoeuvreRepository {
    void add(MainDoeuvre mainOeuvre);
    MainDoeuvre findById(int id);
    List<MainDoeuvre> findAll();
    void delete(int id);

}
