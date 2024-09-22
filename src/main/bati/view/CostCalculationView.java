package main.bati.view;

import main.bati.service.ProjetService;

import java.util.Scanner;

public class CostCalculationView {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjetService projetService;

    public CostCalculationView(ProjetService projetService) {
        this.projetService = projetService;
    }

    public void display() {
        // Logic for calculating project cost
        System.out.println("--- Calcul du coût total ---");
        // Get project details and calculate costs
    }
}