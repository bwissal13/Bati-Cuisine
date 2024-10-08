package main.bati.repository.MainDoeuvre;

import main.bati.model.MainDoeuvre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainDoeuvreRepositoryImpl implements MainDoeuvreRepository {
    private final Connection connection;

    public MainDoeuvreRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    private final List<MainDoeuvre> mainDoeuvresDatabase = new ArrayList<>();
    @Override
    public void add(MainDoeuvre mainDoeuvre, int projectId) {
        String query = "INSERT INTO maindoeuvres (nom, coutUnitaire, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productivite,project_id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mainDoeuvre.getNom());
            stmt.setBigDecimal(2, mainDoeuvre.getCoutUnitaire());
            stmt.setString(3, mainDoeuvre.getTypeComposant());
            stmt.setBigDecimal(4, mainDoeuvre.getTauxTVA());
            stmt.setBigDecimal(5, mainDoeuvre.getTauxHoraire());
            stmt.setInt(6, mainDoeuvre.getHeuresTravail());
            stmt.setBigDecimal(7, mainDoeuvre.getProductivite());
            stmt.setInt(8, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public MainDoeuvre findById(int id) {
        String query = "SELECT * FROM mainoeuvres WHERE id = ?";
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
    public List<MainDoeuvre> findAll() {
        List<MainDoeuvre> mainDoeuvres = new ArrayList<>();
        String query = "SELECT * FROM mainoeuvre";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                mainDoeuvres.add(new MainDoeuvre(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutUnitaire"),
                        rs.getString("typeComposant"),
                        rs.getBigDecimal("tauxTVA"),
                        rs.getBigDecimal("tauxHoraire"),
                        rs.getInt("heuresTravail"),
                        rs.getBigDecimal("productivite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainDoeuvres;
    }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM mainoeuvre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<MainDoeuvre> findByProjectId(int projectId) {
        List<MainDoeuvre> mainDoeuvreList = new ArrayList<>();
        String sql = "SELECT * FROM maindoeuvres WHERE project_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MainDoeuvre mainDoeuvre;
                mainDoeuvre = new MainDoeuvre(
                        rs.getString("nom"),
                        rs.getBigDecimal("coutunitaire"),
                        "Main d'œuvre",
                        rs.getBigDecimal("tauxtva"),
                        rs.getBigDecimal("tauxhoraire"),
                        rs.getInt("heurestravail"),
                        rs.getBigDecimal("productivite")
                );

                mainDoeuvreList.add(mainDoeuvre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mainDoeuvreList;
    }
}
