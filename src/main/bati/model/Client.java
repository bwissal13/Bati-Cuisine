// src/java/model/Client.java
package main.bati.model;

public class Client {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean estProfessionnel;

    // Constructors
    public Client() {}

    public Client(int id, String nom, String adresse, String telephone, boolean estProfessionnel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }

    public Client(String nom, String adresse, String telephone, boolean estProfessionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }
}
