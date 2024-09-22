package main.bati.service;

import main.bati.model.Composant;
import main.bati.repository.Composant.ComposantRepository;


import java.util.List;

public class ComposantService {
    private final ComposantRepository composantRepository;

    // Constructor to inject the repository dependency
    public ComposantService(ComposantRepository composantRepository) {
        this.composantRepository = composantRepository;
    }

    // Add a new Composant
    public void addComposant(Composant composant) {
        composantRepository.add(composant);
        System.out.println("Composant ajouté avec succès.");
    }

    // Get a Composant by its ID
//    public Composant getComposantById(int id) {
//        Composant composant = composantRepository.findById(id);
//        if (composant != null) {
//            return composant;
//        } else {
//            System.out.println("Composant non trouvé.");
//            return null;
//        }
//    }

    // Retrieve all Composants
    public List<Composant> getAllComposants() {
        return composantRepository.findAll();
    }

    // Update an existing Composant
    public void updateComposant(Composant composant) {
        Composant existingComposant = composantRepository.findByName(composant.getNom());
        if (existingComposant != null) {
            composantRepository.update(composant);
            System.out.println("Composant mis à jour avec succès.");
        } else {
            System.out.println("Composant non trouvé. La mise à jour a échoué.");
        }
    }

    // Delete a Composant by its ID
    public void deleteComposant(String nom) {
        Composant composant = composantRepository.findByName(nom);
        if (composant != null) {
            composantRepository.delete(Integer.parseInt(nom));
            System.out.println("Composant supprimé avec succès.");
        } else {
            System.out.println("Composant non trouvé. La suppression a échoué.");
        }
    }
}
