// src/java/repository/DevisRepository.java
package main.bati.repository.Devis;

import main.bati.model.Devis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DevisRepository {
    public void add(Devis devis) ;

    public List<Devis> findAll();

    public Devis findById(int id) ;

    public void update(Devis devis) ;

    public void delete(int id);
}
