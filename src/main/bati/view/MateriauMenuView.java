package main.bati.view;

import main.bati.model.Materiau;
import main.bati.service.MateriauService;
import main.bati.view.MainDoeuvreMenuView;

import java.math.BigDecimal;
import java.util.Scanner;

public class MateriauMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final MateriauService materiauService;
    private final MainDoeuvreMenuView mainDoeuvreMenuView;
    private final int projectId;

    public MateriauMenuView(MateriauService materiauService, MainDoeuvreMenuView mainDoeuvreMenuView, int projectId) {
        this.materiauService = materiauService;
        this.mainDoeuvreMenuView = mainDoeuvreMenuView;
        this.projectId = projectId;
    }

    public void display() {
        boolean addingMaterials = true;

        while (addingMaterials) {
            System.out.println("--- Ajout des matériaux ---");
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = scanner.nextLine();

            System.out.print("Entrez la quantité de ce matériau (en litres) : ");
            int quantite = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Entrez le coût unitaire de ce matériau (€/litre) : ");
            BigDecimal coutUnitaire = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            BigDecimal coutTransport = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            BigDecimal coefficientQualite = scanner.nextBigDecimal();
            scanner.nextLine();

            Materiau materiau = new Materiau(nomMateriau, coutUnitaire, "Matériel", BigDecimal.ZERO, quantite, coutTransport, coefficientQualite);
            materiauService.addMateriau(materiau, projectId);

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                addingMaterials = false;
            }
        }

        mainDoeuvreMenuView.displayMenu();
    }
}
