import main.bati.config.DatabaseConfig;
import main.bati.repository.Client.ClientRepository;
import main.bati.repository.Client.ClientRepositoryImpl;
import main.bati.repository.Devis.DevisRepository;
import main.bati.repository.Devis.DevisRepositoryImpl;
import main.bati.repository.Project.ProjectRepository;
import main.bati.repository.Project.ProjectRepositoryImpl;
import main.bati.service.ClientService;
import main.bati.service.DevisService;
import main.bati.service.ProjetService;
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

        // Initialisation des services avec les repositories correspondants
        ClientService clientService = new ClientService(clientRepository);
        ProjetService projetService = new ProjetService(projetRepository);
        DevisService devisService = new DevisService(devisRepository);

        // Initialisation de la vue principale avec les services et affichage du menu
        MainMenuView mainMenuView = new MainMenuView(clientService, projetService, devisService);
        mainMenuView.display();
    }
}
