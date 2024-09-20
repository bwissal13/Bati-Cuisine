package main.bati.repository.Devis;

import main.bati.model.Devis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevisRepositoryImpl implements DevisRepository {
    private final Connection connection;

    public DevisRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
        public void add(Devis devis) {
        String query = "INSERT INTO devis (montantEstime, dateEmission, dateValidite, accepte) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, devis.getMontantEstime());
            stmt.setDate(2, Date.valueOf(devis.getDateEmission()));
            stmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(4, devis.isAccepte());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
        public List<Devis> findAll() {
        List<Devis> devisList = new ArrayList<>();
        String query = "SELECT * FROM devis";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Devis devis = new Devis(rs.getBigDecimal("montantEstime"),
                        rs.getDate("dateEmission").toLocalDate(),
                        rs.getDate("dateValidite").toLocalDate(),
                        rs.getBoolean("accepte"));
                devisList.add(devis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devisList;
    }
    @Override
        public Devis findById(int id) {
        String query = "SELECT * FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Devis(rs.getBigDecimal("montantEstime"),
                        rs.getDate("dateEmission").toLocalDate(),
                        rs.getDate("dateValidite").toLocalDate(),
                        rs.getBoolean("accepte"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   @Override
        public void update(Devis devis) {
        String query = "UPDATE devis SET montantEstime = ?, dateEmission = ?, dateValidite = ?, accepte = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, devis.getMontantEstime());
            stmt.setDate(2, Date.valueOf(devis.getDateEmission()));
            stmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(4, devis.isAccepte());
            // Set the devis ID here
            stmt.setInt(5, devis.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   @Override
        public void delete(int id) {
        String query = "DELETE FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
