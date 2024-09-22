package main.bati.service;

import main.bati.model.Materiau;
import main.bati.repository.Materiau.MateriauRepository;

import java.util.List;

public class MateriauService {
    private final MateriauRepository materiauRepository;

    public MateriauService(MateriauRepository materiauRepository) {
        this.materiauRepository = materiauRepository;
    }

    // Add a new Materiau
    public void addMateriau(Materiau materiau) {
        materiauRepository.add(materiau);
        System.out.println("Matériau ajouté avec succès.");
    }
}
