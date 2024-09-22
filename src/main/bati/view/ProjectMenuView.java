package main.bati.view;

import main.bati.model.Client;
import main.bati.model.MainDoeuvre;
import main.bati.model.Materiau;
import main.bati.model.Project;
import main.bati.service.ClientService;
import main.bati.service.MainDoeuvreService;
import main.bati.service.MateriauService;
import main.bati.service.ProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjectMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    private final ProjetService projetService;
    private final MateriauService materiauService;
    private final MainDoeuvreService mainDoeuvreService;
    public ProjectMenuView(ClientService clientService, ProjetService projetService, MateriauService materiauService, MainDoeuvreService mainDoeuvreService) {
        this.clientService = clientService;
        this.projetService = projetService;
        this.materiauService= materiauService;
        this.mainDoeuvreService = mainDoeuvreService;

    }

    public void display() {
        System.out.println("--- Recherche de client ---");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        int clientOption = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Client selectedClient;

        switch (clientOption) {
            case 1:
                selectedClient = searchExistingClient();
                break;
            case 2:
                selectedClient = addNewClient();
                break;
            default:
                System.out.println("Option invalide.");
                return;
        }

        if (selectedClient != null) {
            createProject(selectedClient);
        }
    }

    private Client searchExistingClient() {
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();
        Optional<Client> client = clientService.findClientByName(clientName);

        if (client.isPresent()) {
            System.out.println("Client trouvé !");
            Client foundClient = client.get();
            System.out.println("Nom : " + foundClient.getNom());
            System.out.println("Adresse : " + foundClient.getAdress());
            System.out.println("Numéro de téléphone : " + foundClient.getTelephone());
            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                return foundClient;
            } else {
                System.out.println("Opération annulée.");
                return null;
            }
        } else {
            System.out.println("Client non trouvé.");
            return null;
        }
    }

    private Client addNewClient() {
        System.out.print("Nom du client : ");
        String name = scanner.nextLine();
        System.out.print("Adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Téléphone du client : ");
        String phone = scanner.nextLine();
        System.out.print("Le client est-il professionnel ? (true/false) : ");
        boolean isProfessional = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        Client newClient = new Client(name, address, phone, isProfessional);
        clientService.addClient(newClient);
        System.out.println("Client ajouté avec succès !");
        return newClient;
    }

    private void createProject(Client client) {
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();

        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        BigDecimal surface = scanner.nextBigDecimal();
        scanner.nextLine(); // consume newline

        Project newProject = new Project(0, projectName, BigDecimal.ZERO, BigDecimal.ZERO, "0", client.getId());

        projetService.addProject(newProject);
        System.out.println("Projet créé avec succès pour le client " + client.getNom());
        MainDoeuvreMenuView mainDoeuvreMenuView = new MainDoeuvreMenuView(mainDoeuvreService);
        MateriauMenuView materiauMenuView = new MateriauMenuView(materiauService, mainDoeuvreMenuView);

        materiauMenuView.display();

    }

    public void displayProjects() {
        System.out.println("--- Projets Existants ---");
        projetService.getAllProjects().forEach(projet -> {
            System.out.println("Projet ID: " + projet.getId() + ", Nom : " + projet.getNomProjet() +
                    ", Coût Total: " + projet.getCoutTotal() + ", État : " + projet.getEtatProjet());
        });
    }
}
