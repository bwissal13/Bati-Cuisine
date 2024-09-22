package main.bati.model;


import java.math.BigDecimal;

public class Materiau extends Composant {
    private int quantite;
    private BigDecimal coutTransport;
    private BigDecimal coefficientQualite;


    public Materiau(String nom, BigDecimal coutUnitaire, String typeComposant, BigDecimal tauxTVA, int quantite, BigDecimal coutTransport, BigDecimal coefficientQualite) {
        super(nom, coutUnitaire, typeComposant, tauxTVA);
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }


    @Override
    public BigDecimal calculerCoutTotal() {
        return getCoutUnitaire().multiply(new BigDecimal(quantite)).add(coutTransport).multiply(coefficientQualite);
    }

    // Getters and setters
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public BigDecimal getCoutTransport() { return coutTransport; }
    public void setCoutTransport(BigDecimal coutTransport) { this.coutTransport = coutTransport; }

    public BigDecimal getCoefficientQualite() { return coefficientQualite; }
    public void setCoefficientQualite(BigDecimal coefficientQualite) { this.coefficientQualite = coefficientQualite; }
}
