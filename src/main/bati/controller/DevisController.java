// src/java/controller/DevisController.java
package main.bati.controller;

import main.bati.model.Devis;
import main.bati.service.DevisService;

import java.util.List;

public class DevisController {
    private final DevisService devisService;

    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }

    public void addDevis(Devis devis) {
        devisService.addDevis(devis);
    }

    public List<Devis> getAllDevis() {
        return devisService.getAllDevis();
    }

    public Devis getDevisById(int id) {
        return devisService.getDevisById(id);
    }

    public void updateDevis(Devis devis) {
        devisService.updateDevis(devis);
    }

    public void deleteDevis(int id) {
        devisService.deleteDevis(id);
    }
}
