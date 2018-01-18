package com.studiant.com.domain.model;

/**
 * Created by groupama on 28/09/2017.
 */

public class Rib {

    private String idMangoPayUtilisateur;
    private String iban;
    private String bic;
    private String nomPrenomUtilisateur;
    private String adresseUtilisateur;
    private String villeUtilisateur;
    private String codePostalUtilisateur;
    private String active;
    private String crationDate;
    private String ownerName;
    private String id;
    private String type;


    public Rib(String idMangoPayUtilisateur, String iban, String bic, String nomPrenomUtilisateur, String adresseUtilisateur, String villeUtilisateur, String codePostalUtilisateur) {
        this.idMangoPayUtilisateur = idMangoPayUtilisateur;
        this.iban = iban;
        this.bic = bic;
        this.nomPrenomUtilisateur = nomPrenomUtilisateur;
        this.adresseUtilisateur = adresseUtilisateur;
        this.villeUtilisateur = villeUtilisateur;
        this.codePostalUtilisateur = codePostalUtilisateur;
    }

    public Rib(String idMangoPayUtilisateur, String active, String crationDate, String ownerName, String id, String type) {
        this.idMangoPayUtilisateur = idMangoPayUtilisateur;
        this.active = active;
        this.crationDate = crationDate;
        this.ownerName = ownerName;
        this.id = id;
        this.type = type;
    }

    public String getIdMangoPayUtilisateur() {
        return idMangoPayUtilisateur;
    }

    public void setIdMangoPayUtilisateur(String idMangoPayUtilisateur) {
        this.idMangoPayUtilisateur = idMangoPayUtilisateur;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getNomPrenomUtilisateur() {
        return nomPrenomUtilisateur;
    }

    public void setNomPrenomUtilisateur(String nomPrenomUtilisateur) {
        this.nomPrenomUtilisateur = nomPrenomUtilisateur;
    }

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public void setAdresseUtilisateur(String adresseUtilisateur) {
        this.adresseUtilisateur = adresseUtilisateur;
    }

    public String getVilleUtilisateur() {
        return villeUtilisateur;
    }

    public void setVilleUtilisateur(String villeUtilisateur) {
        this.villeUtilisateur = villeUtilisateur;
    }

    public String getCodePostalUtilisateur() {
        return codePostalUtilisateur;
    }

    public void setCodePostalUtilisateur(String codePostalUtilisateur) {
        this.codePostalUtilisateur = codePostalUtilisateur;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCrationDate() {
        return crationDate;
    }

    public void setCrationDate(String crationDate) {
        this.crationDate = crationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
