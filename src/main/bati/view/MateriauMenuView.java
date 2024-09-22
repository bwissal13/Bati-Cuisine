package main.bati.view;

import main.bati.model.Materiau;
import main.bati.service.MateriauService;
import main.bati.view.MainDoeuvreMenuView;

import java.math.BigDecimal;
import java.util.Scanner;

public class MateriauMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final MateriauService materiauService;
    private final MainDoeuvreMenuView mainDoeuvreMenuView; // Add MainDoeuvreMenuView

    // Constructor now takes MainDoeuvreMenuView
    public MateriauMenuView(MateriauService materiauService, MainDoeuvreMenuView mainDoeuvreMenuView) {
        this.materiauService = materiauService;
        this.mainDoeuvreMenuView = mainDoeuvreMenuView;
    }

    public void display() {
        boolean addingMaterials = true;

        while (addingMaterials) {
            System.out.println("--- Ajout des matériaux ---");
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = scanner.nextLine();

            System.out.print("Entrez la quantité de ce matériau (en litres) : ");
            int quantite = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Entrez le coût unitaire de ce matériau (€/litre) : ");
            BigDecimal coutUnitaire = scanner.nextBigDecimal();
            scanner.nextLine(); // consume newline

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            BigDecimal coutTransport = scanner.nextBigDecimal();
            scanner.nextLine(); // consume newline

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            BigDecimal coefficientQualite = scanner.nextBigDecimal();
            scanner.nextLine(); // consume newline

            Materiau materiau = new Materiau(nomMateriau, coutUnitaire, "Matériel", BigDecimal.ZERO, quantite, coutTransport, coefficientQualite);
            materiauService.addMateriau(materiau); // Assuming you have an addMateriau method in your service

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                addingMaterials = false; // Exit the loop if user does not want to add another material
            }
        }

        // Once materials are done, call the MainDoeuvreMenu
        mainDoeuvreMenuView.displayMenu();
    }
}
