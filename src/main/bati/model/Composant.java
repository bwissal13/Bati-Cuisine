package main.bati.model;
import java.math.BigDecimal;

public abstract class Composant {
    private String nom;
    private BigDecimal coutUnitaire;
    private String typeComposant;
    private BigDecimal tauxTVA;

    public Composant(String nom, BigDecimal coutUnitaire, String typeComposant, BigDecimal tauxTVA) {
        this.nom = nom;
        this.coutUnitaire = coutUnitaire;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
    }

    public abstract BigDecimal calculerCoutTotal();

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public BigDecimal getCoutUnitaire() { return coutUnitaire; }
    public String getTypeComposant() { return typeComposant; }
    public BigDecimal getTauxTVA() { return tauxTVA; }
}
