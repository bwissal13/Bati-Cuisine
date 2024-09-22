package main.bati.repository.Project;


import main.bati.model.Project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ProjectRepository {


    public void add(Project projet);

    public List<Project> findAll();

    public Project findById(int id);

    public void update(Project projet);

    public void delete(int id) ;

}
