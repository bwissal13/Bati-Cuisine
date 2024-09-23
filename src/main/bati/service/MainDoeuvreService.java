package main.bati.service;

import main.bati.model.MainDoeuvre;
import main.bati.repository.MainDoeuvre.MainDoeuvreRepository;

import java.util.List;

public class MainDoeuvreService {
    private final MainDoeuvreRepository mainDoeuvreRepository;
    public MainDoeuvreService(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
    }
    public void addMainDoeuvre(MainDoeuvre mainDoeuvre, int projectId) {
        mainDoeuvreRepository.add(mainDoeuvre, projectId);
    }

}
