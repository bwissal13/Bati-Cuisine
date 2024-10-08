package main.bati.model;

import java.math.BigDecimal;

public class MainDoeuvre extends Composant {
    private BigDecimal tauxHoraire;
    private int heuresTravail;
    private BigDecimal productivite;

    public MainDoeuvre(String nom, BigDecimal coutUnitaire, String typeComposant, BigDecimal tauxTVA, BigDecimal tauxHoraire, int heuresTravail, BigDecimal productivite) {
        super(nom, coutUnitaire, typeComposant, tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productivite = productivite;

    }
    @Override
    public BigDecimal calculerCoutTotal() {
        return tauxHoraire.multiply(new BigDecimal(heuresTravail)).multiply(productivite);
    }
    public BigDecimal getTauxHoraire() { return tauxHoraire; }
    public int getHeuresTravail() { return heuresTravail; }
    public BigDecimal getProductivite() { return productivite; }

}
