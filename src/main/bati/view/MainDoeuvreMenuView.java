package main.bati.view;

import main.bati.model.MainDoeuvre;
import main.bati.service.MainDoeuvreService;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainDoeuvreMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final MainDoeuvreService mainDoeuvreService;

    public MainDoeuvreMenuView(MainDoeuvreService mainDoeuvreService) {
        this.mainDoeuvreService = mainDoeuvreService;
    }

    public void displayMenu() {
        boolean addingMainDoeuvre = true;

        while (addingMainDoeuvre) {
            System.out.println("--- Ajout de la main-d'œuvre ---");

            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            BigDecimal tauxHoraire = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume newline

            System.out.print("Entrez le nombre d'heures travaillées : ");
            int heuresTravail = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            BigDecimal productivite = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume newline

            System.out.print("Entrez le coût unitaire (ceci peut correspondre au coût lié à l'employé, aux charges, etc.) : ");
            BigDecimal coutUnitaire = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume newline

            // Assuming tauxTVA and typeComposant are constant for this example
            BigDecimal tauxTVA = BigDecimal.valueOf(0.2);  // Example: 20% VAT
            String typeComposant = "Main-d'œuvre";         // Fixed component type

            // Create MainDoeuvre object
            MainDoeuvre mainDoeuvre = new MainDoeuvre(nom, coutUnitaire, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productivite);

            // Add the main-d'œuvre via the service
            mainDoeuvreService.addMainDoeuvre(mainDoeuvre);

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                addingMainDoeuvre = false;
            }
        }
    }
}
