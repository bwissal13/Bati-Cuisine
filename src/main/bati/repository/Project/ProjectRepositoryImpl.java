package main.bati.repository.Project;

import main.bati.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final Connection connection;

        public ProjectRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
        public void add(Project projet) {
        String query = "INSERT INTO projets (nomProjet, margeBeneficiaire, coutTotal, etatProjet, clientId, devisId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setBigDecimal(2, projet.getMargeBeneficiaire());
            stmt.setBigDecimal(3, projet.getCoutTotal());
            stmt.setString(4, projet.getEtatProjet());
            stmt.setInt(5, projet.getClientId());
            stmt.setInt(6, projet.getDevisId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
        public List<Project> findAll() {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM projets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Project projet = new Project(
                        rs.getInt("id"),
                        rs.getString("nomProjet"),
                        rs.getBigDecimal("margeBeneficiaire"),
                        rs.getBigDecimal("coutTotal"),
                        rs.getString("etatProjet"),
                        rs.getInt("clientId"),
                        rs.getInt("devisId")
                );
                projets.add(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }
    @Override
        public Project findById(int id) {
        String query = "SELECT * FROM projets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                        rs.getInt("id"),
                        rs.getString("nomProjet"),
                        rs.getBigDecimal("margeBeneficiaire"),
                        rs.getBigDecimal("coutTotal"),
                        rs.getString("etatProjet"),
                        rs.getInt("clientId"),
                        rs.getInt("devisId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
        public void update(Project projet) {
        String query = "UPDATE projets SET nomProjet = ?, margeBeneficiaire = ?, coutTotal = ?, etatProjet = ?, clientId = ?, devisId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setBigDecimal(2, projet.getMargeBeneficiaire());
            stmt.setBigDecimal(3, projet.getCoutTotal());
            stmt.setString(4, projet.getEtatProjet());
            stmt.setInt(5, projet.getClientId());
            stmt.setInt(6, projet.getDevisId());
            stmt.setInt(7, projet.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
        public void delete(int id) {
        String query = "DELETE FROM projets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
