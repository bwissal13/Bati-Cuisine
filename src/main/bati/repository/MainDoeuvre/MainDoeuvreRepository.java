package main.bati.repository.MainDoeuvre;

import main.bati.model.MainDoeuvre;

import java.util.List;

public interface MainDoeuvreRepository {
    public void add(MainDoeuvre mainDoeuvre, int projectId);
    MainDoeuvre findById(int id);
    List<MainDoeuvre> findAll();
    void delete(int id);
    List<MainDoeuvre> findByProjectId(int projectId);
}
