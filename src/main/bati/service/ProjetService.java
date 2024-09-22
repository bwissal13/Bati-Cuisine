package main.bati.service;

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

    public void addProject(Project project) {
        if (ValidationUtil.isNotNull(project) && ValidationUtil.isStringValid(project.getNomProjet())) {
            projectRepository.add(project);
        }
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }

    public void updateProject(Project project) {
        if (ValidationUtil.isNotNull(project) && ValidationUtil.isStringValid(project.getNomProjet())) {
            projectRepository.update(project);
        }
    }

    public void deleteProject(int id) {
        projectRepository.delete(id);
    }
    public Project findProjectById(int projectId) {
        return projectRepository.findById(projectId);
    }

    public BigDecimal calculerCoutTotal(int projectId, BigDecimal tvaPercentage, BigDecimal margePercentage) {
        List<Materiau> materiaux = findMateriauxByProjectId(projectId);
        List<MainDoeuvre> mainDoeuvres = findMainDoeuvresByProjectId(projectId);

        BigDecimal totalMateriauxCost = materiaux.stream()
                .map(Materiau::calculerCoutTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMainDoeuvreCost = mainDoeuvres.stream()
                .map(MainDoeuvre::calculerCoutTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCostBeforeTVA = totalMateriauxCost.add(totalMainDoeuvreCost);
        BigDecimal totalCostWithTVA = totalCostBeforeTVA.multiply(BigDecimal.ONE.add(tvaPercentage.divide(BigDecimal.valueOf(100))));
        BigDecimal finalTotalCost = totalCostWithTVA.multiply(BigDecimal.ONE.add(margePercentage.divide(BigDecimal.valueOf(100))));

        return finalTotalCost;
    }

    public Client findClientById(int clientId) {
        return clientRepository.findById(clientId);
    }

    public String findClientAddressById(int clientId) {
        Client client = findClientById(clientId);
        return client != null ? client.getAdress() : "Adresse non disponible";
    }

    public List<Materiau> findMateriauxByProjectId(int projectId) {
        return materiauRepository.findByProjectId(projectId);
    }

    public List<MainDoeuvre> findMainDoeuvresByProjectId(int projectId) {
        return mainDoeuvreRepository.findByProjectId(projectId);
    }
}
