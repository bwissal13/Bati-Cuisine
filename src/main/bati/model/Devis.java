package main.bati.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Devis {
    private int id;
    private BigDecimal montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;

    public Devis(BigDecimal montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMontantEstime() {
        return montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", dateEmission=" + dateEmission +
                ", dateValidite=" + dateValidite +
                ", accepte=" + accepte +
                '}';
    }
}
