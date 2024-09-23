package main.bati.repository.Project;

import main.bati.enumeration.EtatProjet;
import main.bati.model.Project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final Connection connection;
    public ProjectRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Project add(Project projet) {
        String query = "INSERT INTO projets (nomProjet, clientid) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setInt(2, projet.getClientId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        projet.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
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
                        rs.getInt("clientId")

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
                        rs.getInt("clientId")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Project project, EtatProjet etatProjet) {
        String query = "UPDATE public.projets SET margebeneficiaire = ?, couttotal = ?, etatprojet = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, project.getMargeBeneficiaire());
            stmt.setBigDecimal(2, project.getCoutTotal());
            stmt.setString(3, etatProjet.name());
            stmt.setInt(4, project.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Project updated successfully.");
            } else {
                System.out.println("No project found with ID: " + project.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating project: " + e.getMessage());
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
