package main.bati.service;

import main.bati.model.MainDoeuvre;
import main.bati.repository.MainDoeuvre.MainDoeuvreRepository;

import java.util.List;

public class MainDoeuvreService {
    private final MainDoeuvreRepository mainDoeuvreRepository;

    public MainDoeuvreService(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
    }

    public void addMainDoeuvre(MainDoeuvre mainDoeuvre) {
        mainDoeuvreRepository.add(mainDoeuvre);
    }

    public MainDoeuvre getMainDoeuvreById(int id) {
        return mainDoeuvreRepository.findById(id);
    }

    public List<MainDoeuvre> getAllMainDoeuvres() {
        return mainDoeuvreRepository.findAll();
    }

    public void deleteMainDoeuvre(int id) {
        mainDoeuvreRepository.delete(id);
    }
}
