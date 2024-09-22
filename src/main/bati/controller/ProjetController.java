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
        projetService.addProject(projet);
    }

    public List<Project> getAllProjets() {
        return projetService.getAllProjects();
    }

    public Project getProjetById(int id) {
        return projetService.getProjectById(id);
    }

    public void updateProjet(Project projet) {
        projetService.updateProject(projet);
    }

    public void deleteProjet(int id) {
        projetService.deleteProject(id);
    }
}
