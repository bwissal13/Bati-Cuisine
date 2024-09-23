package main.bati.repository.Project;


import main.bati.enumeration.EtatProjet;
import main.bati.model.Project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ProjectRepository {
    public Project add(Project projet);
    public List<Project> findAll();
    public Project findById(int id);
    public void update(Project project, EtatProjet etatProjet);
    public void delete(int id) ;

}
