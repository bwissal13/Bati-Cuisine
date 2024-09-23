package main.bati.view;

import main.bati.model.Client;
import main.bati.service.ClientService;
import java.util.Scanner;

public class ClientMenuView {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientMenuView(ClientService clientService) {
        this.clientService = clientService;
    }

    public void display() {
        while (true) {
            System.out.println("--- Menu Client ---");
            System.out.println("1. Ajouter un nouveau client");
            System.out.println("2. Afficher les clients existants");
            System.out.println("3. Retour au menu principal");
            System.out.print("Choisissez une option : ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    displayClients();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void addNewClient() {
        System.out.print("Nom du client : ");
        String name = scanner.nextLine();
        System.out.print("Adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Téléphone du client : ");
        String phone = scanner.nextLine();
        System.out.print("Le client est-il professionnel ? (true/false) : ");
        boolean isProfessional = scanner.nextBoolean();
        scanner.nextLine();


        Client newClient = new Client(name, address, phone, isProfessional);
        clientService.addClient(newClient);
        System.out.println("Client ajouté avec succès !");
    }

    private void displayClients() {
        System.out.println("--- Liste des Clients ---");
        // Fetch and display the list of clients from the service
        clientService.getAllClients().forEach(client -> {
            System.out.println("Client : " + client.getNom() + ", Adresse : " + client.getAdress() +
                    ", Téléphone : " + client.getTelephone() +
                    ", Professionnel : " + (client.isEstProfessionnel() ? "Oui" : "Non"));
        });
    }
}
