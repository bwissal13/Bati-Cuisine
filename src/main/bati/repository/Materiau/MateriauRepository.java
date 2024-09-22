package main.bati.repository.Materiau;

import main.bati.model.Materiau;

import java.util.List;

public interface MateriauRepository {
    void add(Materiau materiau);
    Materiau findById(int id);
    List<Materiau> findAll();
    void delete(int id);
    List<Materiau> findByProjectId(int projectId);
}
