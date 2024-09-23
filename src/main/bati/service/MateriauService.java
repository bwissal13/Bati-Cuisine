package main.bati.service;

import main.bati.model.Materiau;
import main.bati.model.Project;
import main.bati.repository.Materiau.MateriauRepository;

import java.util.List;

public class MateriauService {
    private final MateriauRepository materiauRepository;
    public MateriauService(MateriauRepository materiauRepository) {
        this.materiauRepository = materiauRepository;
    }
    public void addMateriau(Materiau materiau, int projectId) {
        materiauRepository.add(materiau, projectId);
        System.out.println("Matériau ajouté avec succès.");
    }

}
