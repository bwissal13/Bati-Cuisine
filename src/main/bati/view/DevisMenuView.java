package main.bati.view;

import main.bati.enumeration.EtatProjet;
import main.bati.model.Devis;
import main.bati.model.Project;
import main.bati.service.DevisService;
import main.bati.service.ProjetService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DevisMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final DevisService devisService;
    private final ProjetService projetService;

    public DevisMenuView(DevisService devisService, ProjetService projetService) {
        this.devisService = devisService;
        this.projetService = projetService;
    }

    public void display(Project project) {
        int projectId = project.getId();

        System.out.println("--- Enregistrement du Devis pour le Projet ID : " + projectId + " ---");

        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmissionStr = scanner.nextLine();
        LocalDate dateEmission = LocalDate.parse(dateEmissionStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValiditeStr = scanner.nextLine();
        LocalDate dateValidite = LocalDate.parse(dateValiditeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez le montant estimé du devis : ");
        BigDecimal montantEstime = new BigDecimal(scanner.nextLine());

        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            Devis newDevis = new Devis(montantEstime, dateEmission, dateValidite, true);
            devisService.addDevis(newDevis);
            LocalDate today = LocalDate.now();
            if (!today.isBefore(dateEmission) && !today.isAfter(dateValidite)) {
                projetService.updateProject(project,EtatProjet.TERMINE);

            } else {
                projetService.updateProject(project,EtatProjet.ANNULE);
            }

            System.out.println("Devis enregistré avec succès et état du projet mis à jour !");
        } else {
            System.out.println("Enregistrement du devis annulé.");
        }
    }
}
