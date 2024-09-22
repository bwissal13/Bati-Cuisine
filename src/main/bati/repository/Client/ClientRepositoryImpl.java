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
        String query = "INSERT INTO clients (nom, adresse, telephone, estprofessionnel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            System.out.println(client.getAdress());
            stmt.setString(2, client.getAdress());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstProfessionnel());
            stmt.executeUpdate();
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
    public Client findById(int id) {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estProfessionnel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String query = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, estProfessionnel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdress());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstProfessionnel());
            stmt.setInt(5, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
