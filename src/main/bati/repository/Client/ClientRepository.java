package main.bati.repository.Client;

import main.bati.model.Client;

import java.util.List;

public interface ClientRepository {
    void add(Client client);

    List<Client> findAll();

    Client findById(int id);

    void update(Client client);

    void delete(int id);
}
