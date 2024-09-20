package main.bati.service;


import main.bati.model.Devis;
import main.bati.repository.Devis.DevisRepository;
import main.bati.util.ValidationUtil;

import java.util.List;

public class DevisService {
    private final DevisRepository devisRepository;

    public DevisService(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    public void addDevis(Devis devis) {
        if (ValidationUtil.isNotNull(devis) && ValidationUtil.isStringValid(devis.getMontantEstime().toString())) {
            devisRepository.add(devis);
        }
    }

    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    public Devis getDevisById(int id) {
        return devisRepository.findById(id);
    }

    public void updateDevis(Devis devis) {
        if (ValidationUtil.isNotNull(devis) && ValidationUtil.isStringValid(devis.getMontantEstime().toString())) {
            devisRepository.update(devis);
        }
    }

    public void deleteDevis(int id) {
        devisRepository.delete(id);
    }
}
