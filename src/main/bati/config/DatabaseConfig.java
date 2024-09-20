package main.bati.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    // Constructeur privé pour empêcher l'instanciation directe
    private DatabaseConfig() {
        try {
            // Chargement des propriétés de configuration
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("database.properties"));

            // Récupération des informations de connexion à partir du fichier de propriétés
            this.url = props.getProperty("db.url");
            this.username = props.getProperty("db.username");
            this.password = props.getProperty("db.password");

            // Création de la connexion à la base de données
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | NullPointerException | IOException e) {
            e.printStackTrace();  // Gestion de l'erreur si la connexion échoue
        }
    }

    // Méthode publique pour obtenir l'instance unique de DatabaseConfig
    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion à la base de données
    public Connection getConnection() {
        return connection;
    }
}
