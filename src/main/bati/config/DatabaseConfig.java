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


    private DatabaseConfig() {
        try {

            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("database.properties"));

            this.url = props.getProperty("db.url");
            this.username = props.getProperty("db.username");
            this.password = props.getProperty("db.password");

            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
