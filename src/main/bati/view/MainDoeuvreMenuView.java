package main.bati.view;

import main.bati.model.MainDoeuvre;
import main.bati.service.DevisService;
import main.bati.service.MainDoeuvreService;
import main.bati.service.ProjetService; // Import the ProjetService

import java.math.BigDecimal;
import java.util.Scanner;

public class MainDoeuvreMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final MainDoeuvreService mainDoeuvreService;
    private final ProjetService projetService;
private final DevisService devisService;
    private final int projectId;


    public MainDoeuvreMenuView(MainDoeuvreService mainDoeuvreService, ProjetService projetService,DevisService devisService, int projectId) {
        this.mainDoeuvreService = mainDoeuvreService;
        this.projetService = projetService;
        this.devisService = devisService;
        this.projectId = projectId;
    }

    public void displayMenu() {
        boolean addingMainDoeuvre = true;

        while (addingMainDoeuvre) {
            System.out.println("--- Ajout de la main-d'œuvre ---");

            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            BigDecimal tauxHoraire = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Entrez le nombre d'heures travaillées : ");
            int heuresTravail = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            BigDecimal productivite = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Entrez le coût unitaire (ceci peut correspondre au coût lié à l'employé, aux charges, etc.) : ");
            BigDecimal coutUnitaire = scanner.nextBigDecimal();
            scanner.nextLine();

            BigDecimal tauxTVA = BigDecimal.valueOf(0.2);
            String typeComposant = "Main-d'œuvre";

            MainDoeuvre mainDoeuvre = new MainDoeuvre(nom, coutUnitaire, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productivite);

            mainDoeuvreService.addMainDoeuvre(mainDoeuvre, projectId);

            System.out.println("Main-d'œuvre ajoutée avec succès !");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                addingMainDoeuvre = false;
            }

            CostCalculationView costCalculationView = new CostCalculationView(projetService,devisService);
            costCalculationView.display();
        }
    }
}
