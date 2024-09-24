
package main.bati.view;

import main.bati.enumeration.EtatProjet;
import main.bati.model.Materiau;
import main.bati.model.MainDoeuvre;
import main.bati.model.Project;
import main.bati.service.DevisService;
import main.bati.service.ProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CostCalculationView {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjetService projetService;
    private final DevisService devisService;

    public CostCalculationView(ProjetService projetService, DevisService devisService) {
        this.projetService = projetService;
        this.devisService = devisService;
    }

    public void display() {
        System.out.print("Entrez l'ID du projet : ");
        int projectId = scanner.nextInt();
        scanner.nextLine();

        Project project = projetService.getProjectById(projectId);
        System.out.println("--- Calcul du coût total ---");

        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        boolean applyTva = scanner.nextLine().equalsIgnoreCase("y");

        BigDecimal tvaPercentage = BigDecimal.ZERO;
        if (applyTva) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            tvaPercentage = scanner.nextBigDecimal();
            scanner.nextLine();
        }

        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        boolean applyProfitMargin = scanner.nextLine().equalsIgnoreCase("y");

        BigDecimal profitMarginPercentage = BigDecimal.ZERO;
        if (applyProfitMargin) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            profitMarginPercentage = scanner.nextBigDecimal();
            scanner.nextLine();
        }

        System.out.println("Calcul du coût en cours...");


        List<Materiau> materiaux = projetService.getMateriauxForProject(projectId);
        List<MainDoeuvre> mainDoeuvreList = projetService.getMainDoeuvreForProject(projectId);


        System.out.println("1. Matériaux :");
        for (Materiau materiau : materiaux) {
            BigDecimal coutTotal = materiau.calculerCoutTotal();
            System.out.println("- " + materiau.getNom() + " : " + coutTotal + " € " +
                    "(quantité : " + materiau.getQuantite() +
                    ", coût unitaire : " + materiau.getCoutUnitaire() + " €" +
                    ", qualité : " + materiau.getCoefficientQualite() + ", transport : " + materiau.getCoutTransport() + " €)");
        }

        BigDecimal totalMateriaux = projetService.calculateTotalMaterialCost(projectId);
        System.out.println("**Coût total des matériaux avant TVA : " + totalMateriaux + " €**");

        if (applyTva) {
            BigDecimal totalMateriauxAvecTva = totalMateriaux.add(totalMateriaux.multiply(tvaPercentage).divide(BigDecimal.valueOf(100)));
            System.out.println("**Coût total des matériaux avec TVA (" + tvaPercentage + "%) : " + totalMateriauxAvecTva + " €**");
        }

        System.out.println("2. Main-d'œuvre :");
        for (MainDoeuvre mainDoeuvre : mainDoeuvreList) {
            BigDecimal coutTotal = mainDoeuvre.calculerCoutTotal();
            System.out.println("- " + mainDoeuvre.getNom() + " : " + coutTotal + " € " +
                    "(taux horaire : " + mainDoeuvre.getTauxHoraire() + " €/h" +
                    ", heures travaillées : " + mainDoeuvre.getHeuresTravail() + " h" +
                    ", productivité : " + mainDoeuvre.getProductivite() + ")");
        }

        BigDecimal totalMainDoeuvre = projetService.calculateTotalLaborCost(projectId);
        System.out.println("**Coût total de la main-d'œuvre avant TVA : " + totalMainDoeuvre + " €**");

        BigDecimal totalCostBeforeProfit = totalMateriaux.add(totalMainDoeuvre);
        System.out.println("3. Coût total avant marge : " + totalCostBeforeProfit + " €");
        if (applyProfitMargin) {
            BigDecimal profitMargin = totalCostBeforeProfit.multiply(profitMarginPercentage).divide(BigDecimal.valueOf(100));
            System.out.println("4. Marge bénéficiaire (" + profitMarginPercentage + "%) : " + profitMargin + " €");

            BigDecimal totalFinalCost = totalCostBeforeProfit.add(profitMargin);
            System.out.println("**Coût total final du projet : " + totalFinalCost + " €**");

            project.setMargeBeneficiaire(profitMargin);
            project.setCoutTotal(totalFinalCost);
            projetService.updateProject(project, EtatProjet.EN_COURS);
        } else {
            System.out.println("**Coût total final du projet : " + totalCostBeforeProfit + " €**");

            project.setMargeBeneficiaire(BigDecimal.ZERO);
            project.setCoutTotal(totalCostBeforeProfit);
            projetService.updateProject(project, EtatProjet.EN_COURS);
        }

        DevisMenuView devisMenuView = new DevisMenuView(devisService, projetService);
        devisMenuView.display(project);
    }
}
