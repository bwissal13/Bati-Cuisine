package main.bati.model;

import java.math.BigDecimal;

public class Project {
    private int id;
    private String nomProjet;
    private BigDecimal margeBeneficiaire;
    private BigDecimal coutTotal;
    private String etatProjet;
    private int clientId;
    private int devisId;

    public Project(int id, String nomProjet, BigDecimal margeBeneficiaire, BigDecimal coutTotal, String etatProjet, int clientId, int devisId) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.clientId = clientId;
        this.devisId = devisId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public BigDecimal getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(BigDecimal margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public BigDecimal getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(BigDecimal coutTotal) {
        this.coutTotal = coutTotal;
    }

    public String getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(String etatProjet) {
        this.etatProjet = etatProjet;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDevisId() {
        return devisId;
    }

    public void setDevisId(int devisId) {
        this.devisId = devisId;
    }
}
