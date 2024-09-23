package main.bati.repository.Composant;

import main.bati.model.Composant;
import main.bati.model.Materiau;;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComposantRepositoryImpl implements ComposantRepository {
    private final Connection connection;
    public ComposantRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void add(Composant composant) {
        String query = "INSERT INTO composants (nom, coutUnitaire, typeComposant, tauxTVA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, composant.getNom());
            stmt.setBigDecimal(2, composant.getCoutUnitaire());
            stmt.setString(3, composant.getTypeComposant());
            stmt.setBigDecimal(4, composant.getTauxTVA());
            stmt.executeUpdate();
            System.out.println("Composant added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Composant findByName(String nom) {
        String query = "SELECT * FROM composants WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractComposantFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Composant> findAll() {
        String query = "SELECT * FROM composants";
        List<Composant> composants = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Composant composant = extractComposantFromResultSet(rs);
                composants.add(composant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return composants;
    }
    @Override
    public void update(Composant composant) {
        String query = "UPDATE composants SET nom = ?, coutUnitaire = ?, typeComposant = ?, tauxTVA = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, composant.getNom());
            stmt.setBigDecimal(2, composant.getCoutUnitaire());
            stmt.setString(3, composant.getTypeComposant());
            stmt.setBigDecimal(4, composant.getTauxTVA());
//            stmt.setInt(5, composant.getId()); // Assuming you have an `id` field in the Composant class
            stmt.executeUpdate();
            System.out.println("Composant updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM composants WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Composant deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Composant extractComposantFromResultSet(ResultSet rs) throws SQLException {
        String nom = rs.getString("nom");
        BigDecimal coutUnitaire = rs.getBigDecimal("coutUnitaire");
        String typeComposant = rs.getString("typeComposant");
        BigDecimal tauxTVA = rs.getBigDecimal("tauxTVA");

        // Assuming Composant is abstract and you're working with its subclasses
        return new Materiau(nom, coutUnitaire, typeComposant, tauxTVA, 0, BigDecimal.ZERO, BigDecimal.ONE);
    }
}
