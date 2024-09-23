package main.bati.repository.Materiau;

import main.bati.model.Materiau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriauRepositoryImpl implements MateriauRepository {
    private final Connection connection;

    public MateriauRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void add(Materiau materiau, int projectId) {
        String query = "INSERT INTO materiaux (nom, coutUnitaire, typeComposant, tauxTVA, quantite, coutTransport, coefficientQualite, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, materiau.getNom());
            stmt.setBigDecimal(2, materiau.getCoutUnitaire());
            stmt.setString(3, materiau.getTypeComposant());
            stmt.setBigDecimal(4, materiau.getTauxTVA());
            stmt.setInt(5, materiau.getQuantite());
            stmt.setBigDecimal(6, materiau.getCoutTransport());
            stmt.setBigDecimal(7, materiau.getCoefficientQualite());
            stmt.setInt(8, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Materiau findById(int id) {
        String query = "SELECT * FROM materiau WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Materiau(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutUnitaire"),
                        rs.getString("typeComposant"),
                        rs.getBigDecimal("tauxTVA"),
                        rs.getInt("quantite"),
                        rs.getBigDecimal("coutTransport"),
                        rs.getBigDecimal("coefficientQualite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Materiau> findAll() {
        List<Materiau> materiaux = new ArrayList<>();
        String query = "SELECT * FROM materiau";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                materiaux.add(new Materiau(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutUnitaire"),
                        rs.getString("typeComposant"),
                        rs.getBigDecimal("tauxTVA"),
                        rs.getInt("quantite"),
                        rs.getBigDecimal("coutTransport"),
                        rs.getBigDecimal("coefficientQualite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiaux;
    }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM materiau WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Materiau> findByProjectId(int projectId) {
        List<Materiau> materiaux = new ArrayList<>();
        String sql = "SELECT * FROM materiaux WHERE project_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Materiau materiau = new Materiau(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutunitaire"),
                        rs.getString("typecomposant"),
                        rs.getBigDecimal("tauxtva"),
                        rs.getInt("quantite"),
                        rs.getBigDecimal("couttransport"),
                        rs.getBigDecimal("coefficientqualite")
                );
                materiaux.add(materiau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return materiaux;
    }
}
