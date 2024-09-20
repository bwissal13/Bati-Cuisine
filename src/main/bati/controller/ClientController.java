package main.bati.controller;

import main.bati.model.Client;
import main.bati.service.ClientService;

import java.util.List;

public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(Client client) {
        clientService.addClient(client);
    }

    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    public Client getClientById(int id) {
        return clientService.getClientById(id);
    }

    public void updateClient(Client client) {
        clientService.updateClient(client);
    }

    public void deleteClient(int id) {
        clientService.deleteClient(id);
    }
}
