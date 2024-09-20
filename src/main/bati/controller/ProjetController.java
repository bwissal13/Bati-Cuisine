package main.bati.controller;

import main.bati.model.Project;
import main.bati.service.ProjetService;

import java.util.List;

public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    public void addProjet(Project projet) {
        projetService.addProjet(projet);
    }

    public List<Project> getAllProjets() {
        return projetService.getAllProjets();
    }

    public Project getProjetById(int id) {
        return projetService.getProjetById(id);
    }

    public void updateProjet(Project projet) {
        projetService.updateProjet(projet);
    }

    public void deleteProjet(int id) {
        projetService.deleteProjet(id);
    }
}
