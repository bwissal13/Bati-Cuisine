package main.bati.service;

import main.bati.enumeration.EtatProjet;
import main.bati.model.Client;
import main.bati.model.MainDoeuvre;
import main.bati.model.Materiau;
import main.bati.model.Project;
import main.bati.repository.Client.ClientRepository;
import main.bati.repository.MainDoeuvre.MainDoeuvreRepository;
import main.bati.repository.Materiau.MateriauRepository;
import main.bati.repository.Project.ProjectRepository;
import main.bati.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.List;

public class ProjetService {
    private final ProjectRepository projectRepository;
    private final MateriauRepository materiauRepository;
    private final MainDoeuvreRepository mainDoeuvreRepository;
    private final ClientRepository clientRepository;

    public ProjetService(ProjectRepository projectRepository, MateriauRepository materiauRepository, MainDoeuvreRepository mainDoeuvreRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.materiauRepository = materiauRepository;
        this.mainDoeuvreRepository = mainDoeuvreRepository;
        this.clientRepository = clientRepository;
    }

    public Project addProject(Project project) {
        if (ValidationUtil.isNotNull(project) && ValidationUtil.isStringValid(project.getNomProjet())) {
            projectRepository.add(project);
        }
        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }
    public void updateProject(Project project,EtatProjet etatProjet) {
        if (ValidationUtil.isNotNull(project) && ValidationUtil.isStringValid(project.getNomProjet())) {
            projectRepository.update(project, etatProjet);
        }
    }
    public List<Materiau> getMateriauxForProject(int projectId) {
        return materiauRepository.findByProjectId(projectId);
    }
    public List<MainDoeuvre> getMainDoeuvreForProject(int projectId) {
        return mainDoeuvreRepository.findByProjectId(projectId);
    }
    public BigDecimal calculateTotalMaterialCost(int projectId) {
        List<Materiau> materiaux = getMateriauxForProject(projectId);
        return materiaux.stream()
                .map(Materiau::calculerCoutTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public BigDecimal calculateTotalLaborCost(int projectId) {
        List<MainDoeuvre> mainDoeuvreList = getMainDoeuvreForProject(projectId);
        return mainDoeuvreList.stream()
                .map(MainDoeuvre::calculerCoutTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
