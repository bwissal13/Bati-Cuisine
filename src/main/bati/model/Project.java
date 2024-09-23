package main.bati.model;

import java.math.BigDecimal;
import java.util.List;

public class Project {
    private int id;
    private String nomProjet;
    private BigDecimal margeBeneficiaire;
    private BigDecimal coutTotal;
    private String etatProjet;
    private int clientId;

    public Project(int id, String nomProjet, BigDecimal margeBeneficiaire, BigDecimal coutTotal, String etatProjet, int clientId) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.clientId = clientId;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNomProjet() {
        return nomProjet;
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
    public int getClientId() {
        return clientId;
    }

}
