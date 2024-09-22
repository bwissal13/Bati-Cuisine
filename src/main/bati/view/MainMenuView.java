package main.bati.view;

import main.bati.service.*;

import java.util.Scanner;

public class MainMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    private final ProjetService projetService;
    private final DevisService devisService;
    private final MateriauService materiauService;
    private final MainDoeuvreService mainDoeuvreService;

    public MainMenuView(ClientService clientService, ProjetService projetService, DevisService devisService,MateriauService materiauService, MainDoeuvreService mainDoeuvreService ) {
        this.clientService = clientService;
        this.projetService = projetService;
        this.devisService = devisService;
        this.materiauService = materiauService;
        this.mainDoeuvreService = mainDoeuvreService;
    }

    public void display() {
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    new ProjectMenuView(clientService, projetService, materiauService,mainDoeuvreService).display();
                    break;
                case 2:
                    new ProjectMenuView(clientService, projetService, materiauService,mainDoeuvreService).displayProjects();
                    break;
                case 3:
                    new CostCalculationView(projetService).display();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
