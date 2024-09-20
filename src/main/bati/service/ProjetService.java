package main.bati.service;

import main.bati.model.Project;
import main.bati.repository.Project.ProjectRepository;
import main.bati.util.ValidationUtil;

import java.util.List;

public class ProjetService {
    private final ProjectRepository projetRepository;

    public ProjetService(ProjectRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public void addProjet(Project projet) {
        if (ValidationUtil.isNotNull(projet) && ValidationUtil.isStringValid(projet.getNomProjet())) {
            projetRepository.add(projet);
        }
    }

    public List<Project> getAllProjets() {
        return projetRepository.findAll();
    }

    public Project getProjetById(int id) {
        return projetRepository.findById(id);
    }

    public void updateProjet(Project projet) {
        if (ValidationUtil.isNotNull(projet) && ValidationUtil.isStringValid(projet.getNomProjet())) {
            projetRepository.update(projet);
        }
    }

    public void deleteProjet(int id) {
        projetRepository.delete(id);
    }
}
