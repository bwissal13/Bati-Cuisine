package main.bati.repository.Client;

import main.bati.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Client client) {
        String query = "INSERT INTO clients (nom, adresse, telephone, estprofessionnel) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdress());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstProfessionnel());

            ResultSet generatedKeys = stmt.executeQuery();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Client client = new Client(
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estProfessionnel"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    @Override
    public Optional<Client> findByName(String clientName) {
        String query = "SELECT * FROM clients WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estProfessionnel")
                );
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
