package main.bati.view;

import main.bati.model.Project;
import main.bati.model.Materiau;
import main.bati.model.MainDoeuvre;
import main.bati.service.ProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CostCalculationView {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjetService projetService;

    public CostCalculationView(ProjetService projetService) {
        this.projetService = projetService;
    }

    public void display() {
        System.out.println("--- Calcul du coût total ---");

        // Select project
        System.out.print("Entrez l'ID du projet : ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // consume newline

        Project project = projetService.findProjectById(projectId);

        // Apply VAT
        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        boolean applyTVA = scanner.nextLine().equalsIgnoreCase("y");
        BigDecimal tvaPercentage = BigDecimal.ZERO;
        if (applyTVA) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            tvaPercentage = scanner.nextBigDecimal();
            scanner.nextLine(); // consume newline
        }

        // Apply profit margin
        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        boolean applyMarge = scanner.nextLine().equalsIgnoreCase("y");
        BigDecimal margePercentage = BigDecimal.ZERO;
        if (applyMarge) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            margePercentage = scanner.nextBigDecimal();
            scanner.nextLine(); // consume newline
        }

        // Calculate costs
        System.out.println("Calcul du coût en cours...");
        BigDecimal totalCost = projetService.calculerCoutTotal(projectId, tvaPercentage, margePercentage);

        // Display results
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + project.getNomProjet());
        // Assuming there is a way to get client details and address from the project or a related service
        System.out.println("Client : " + projetService.findClientById(project.getClientId()).getNom());
        System.out.println("Adresse du chantier : " + projetService.findClientAddressById(project.getClientId()));
        // Add other project details like surface area if available

        // Display cost breakdown
        List<Materiau> materiaux = projetService.findMateriauxByProjectId(projectId);
        List<MainDoeuvre> mainDoeuvres = projetService.findMainDoeuvresByProjectId(projectId);

        System.out.println("--- Détail des Coûts ---");
        System.out.println("1. Matériaux :");
        BigDecimal totalMateriauxCost = BigDecimal.ZERO;
        for (Materiau materiau : materiaux) {
            BigDecimal coutTotal = materiau.calculerCoutTotal();
            totalMateriauxCost = totalMateriauxCost.add(coutTotal);
            System.out.printf("- %s : %.2f € (quantité : %d, coût unitaire : %.2f €/unité, transport : %.2f €, qualité : %.2f)\n",
                    materiau.getNom(), coutTotal, materiau.getQuantite(), materiau.getCoutUnitaire(), materiau.getCoutTransport(), materiau.getCoefficientQualite());
        }
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", totalMateriauxCost);
        BigDecimal totalMateriauxWithTVA = totalMateriauxCost.multiply(BigDecimal.ONE.add(tvaPercentage.divide(BigDecimal.valueOf(100))));
        System.out.printf("**Coût total des matériaux avec TVA (%.2f%%) : %.2f €**\n", tvaPercentage, totalMateriauxWithTVA);

        System.out.println("2. Main-d'œuvre :");
        BigDecimal totalMainDoeuvreCost = BigDecimal.ZERO;
        for (MainDoeuvre mainDoeuvre : mainDoeuvres) {
            BigDecimal coutTotal = mainDoeuvre.calculerCoutTotal();
            totalMainDoeuvreCost = totalMainDoeuvreCost.add(coutTotal);
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %d h, productivité : %.2f)\n",
                    mainDoeuvre.getNom(), coutTotal, mainDoeuvre.getTauxHoraire(), mainDoeuvre.getHeuresTravail(), mainDoeuvre.getProductivite());
        }
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", totalMainDoeuvreCost);
        BigDecimal totalMainDoeuvreWithTVA = totalMainDoeuvreCost.multiply(BigDecimal.ONE.add(tvaPercentage.divide(BigDecimal.valueOf(100))));
        System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.2f%%) : %.2f €**\n", tvaPercentage, totalMainDoeuvreWithTVA);

        BigDecimal totalCostBeforeMarge = totalMateriauxWithTVA.add(totalMainDoeuvreWithTVA);
        System.out.printf("3. Coût total avant marge : %.2f €\n", totalCostBeforeMarge);

        BigDecimal marge = totalCostBeforeMarge.multiply(margePercentage.divide(BigDecimal.valueOf(100)));
        System.out.printf("4. Marge bénéficiaire (%.2f%%) : %.2f €\n", margePercentage, marge);

        BigDecimal finalTotalCost = totalCostBeforeMarge.add(marge);
        System.out.printf("**Coût total final du projet : %.2f €**\n", finalTotalCost);
    }
}
