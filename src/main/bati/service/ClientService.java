package main.bati.service;


import main.bati.model.Client;
import main.bati.repository.Client.ClientRepository;
import main.bati.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        if (ValidationUtil.isNotNull(client) && ValidationUtil.isStringValid(client.getNom())) {
            clientRepository.add(client);
       }
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id);
    }

    public void updateClient(Client client) {
        if (ValidationUtil.isNotNull(client) && ValidationUtil.isStringValid(client.getNom())) {
            clientRepository.update(client);
        }
    }

    public void deleteClient(int id) {
        clientRepository.delete(id);
    }
    public Optional<Client> findClientByName(String clientName) {
        return clientRepository.findByName(clientName);
    }

}
