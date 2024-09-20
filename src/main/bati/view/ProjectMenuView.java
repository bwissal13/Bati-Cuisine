// src/java/view/ProjectMenuView.java
package main.bati.view;

import main.bati.model.Client;
import main.bati.model.Project;
import main.bati.service.ClientService;
import main.bati.service.ProjetService;

import java.util.Scanner;

public class ProjectMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    private final ProjetService projetService;

    public ProjectMenuView(ClientService clientService, ProjetService projetService) {
        this.clientService = clientService;
        this.projetService = projetService;
    }

    public void display() {
        // Logic for creating a new project, similar to previous implementation
        System.out.println("--- Création d'un Nouveau Projet ---");
        // Add logic to get client, project details, materials, and labor
    }

    public void displayProjects() {
        // Logic for displaying existing projects
        System.out.println("--- Projets Existants ---");
        // Fetch and display projects from projetService
    }

    private Client addNewClient() {
        // Logic to add a new client
        return null; // Return the created client
    }

    private Project createProject(String projectName, Client client) {
        // Logic to create and return a project
        return null;
    }

    private void addMaterialsToProject(Project projet) {
        // Logic for adding materials
    }

    private void addLaborToProject(Project projet) {
        // Logic for adding labor
    }
}
