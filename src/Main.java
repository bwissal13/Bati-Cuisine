import main.bati.config.DatabaseConfig;
import main.bati.repository.Client.ClientRepository;
import main.bati.repository.Client.ClientRepositoryImpl;
import main.bati.repository.Devis.DevisRepository;
import main.bati.repository.Devis.DevisRepositoryImpl;
import main.bati.repository.MainDoeuvre.MainDoeuvreRepository;
import main.bati.repository.MainDoeuvre.MainDoeuvreRepositoryImpl;
import main.bati.repository.Materiau.MateriauRepository;
import main.bati.repository.Materiau.MateriauRepositoryImpl;
import main.bati.repository.Project.ProjectRepository;
import main.bati.repository.Project.ProjectRepositoryImpl;
import main.bati.service.*;
import main.bati.view.MainDoeuvreMenuView;
import main.bati.view.MainMenuView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Initialisation de la configuration de la base de données via le singleton DatabaseConfig
        DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

        // Récupération de la connexion à la base de données
        Connection connection = databaseConfig.getConnection();

        // Initialisation des repositories avec la connexion de la base de données
        ClientRepository clientRepository = new ClientRepositoryImpl(connection);
        ProjectRepository projetRepository = new ProjectRepositoryImpl(connection);
        DevisRepository devisRepository = new DevisRepositoryImpl(connection);
        MateriauRepository materiauRepository = new MateriauRepositoryImpl(connection);
        MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepositoryImpl(connection);
        // Initialisation des services avec les repositories correspondants
        ClientService clientService = new ClientService(clientRepository);
        ProjetService projetService = new ProjetService(projetRepository,materiauRepository,mainDoeuvreRepository,clientRepository);
        DevisService devisService = new DevisService(devisRepository);
        MateriauService materiauService = new MateriauService(materiauRepository);
        MainDoeuvreService mainDoeuvreService = new MainDoeuvreService(mainDoeuvreRepository);
        // Initialisation de la vue principale avec les services et affichage du menu
        MainMenuView mainMenuView = new MainMenuView(clientService, projetService, devisService,materiauService,mainDoeuvreService);

        mainMenuView.display();
    }
}
