package main.bati.repository.MainDoeuvre;

import main.bati.model.MainDoeuvre;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainDoeuvreRepositoryImpl implements MainDoeuvreRepository {

    private final Connection connection;

    public MainDoeuvreRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(MainDoeuvre mainDoeuvre) {
        String query = "INSERT INTO maindoeuvre (nom, coutUnitaire, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productivite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mainDoeuvre.getNom());
            stmt.setBigDecimal(2, mainDoeuvre.getCoutUnitaire());
            stmt.setString(3, mainDoeuvre.getTypeComposant());
            stmt.setBigDecimal(4, mainDoeuvre.getTauxTVA());
            stmt.setBigDecimal(5, mainDoeuvre.getTauxHoraire());
            stmt.setInt(6, mainDoeuvre.getHeuresTravail());
            stmt.setBigDecimal(7, mainDoeuvre.getProductivite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la main d'œuvre : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<MainDoeuvre> findAll() {
        List<MainDoeuvre> mainDoeuvreList = new ArrayList<>();
        String query = "SELECT * FROM maindoeuvre";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                MainDoeuvre mainDoeuvre = new MainDoeuvre(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutUnitaire"),
                        rs.getString("typeComposant"),
                        rs.getBigDecimal("tauxTVA"),
                        rs.getBigDecimal("tauxHoraire"),
                        rs.getInt("heuresTravail"),
                        rs.getBigDecimal("productivite")
                );
                mainDoeuvreList.add(mainDoeuvre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainDoeuvreList;
    }

    @Override
    public MainDoeuvre findById(int id) {
        String query = "SELECT * FROM maindoeuvre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MainDoeuvre(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutUnitaire"),
                        rs.getString("typeComposant"),
                        rs.getBigDecimal("tauxTVA"),
                        rs.getBigDecimal("tauxHoraire"),
                        rs.getInt("heuresTravail"),
                        rs.getBigDecimal("productivite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(MainDoeuvre mainDoeuvre) {
        String query = "UPDATE maindoeuvre SET nom = ?, coutUnitaire = ?, typeComposant = ?, tauxTVA = ?, " +
                "tauxHoraire = ?, heuresTravail = ?, productivite = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mainDoeuvre.getNom());
            stmt.setBigDecimal(2, mainDoeuvre.getCoutUnitaire());
            stmt.setString(3, mainDoeuvre.getTypeComposant());
            stmt.setBigDecimal(4, mainDoeuvre.getTauxTVA());
            stmt.setBigDecimal(5, mainDoeuvre.getTauxHoraire());
            stmt.setInt(6, mainDoeuvre.getHeuresTravail());
            stmt.setBigDecimal(7, mainDoeuvre.getProductivite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM maindoeuvre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
