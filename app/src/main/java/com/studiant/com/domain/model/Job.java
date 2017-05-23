package com.studiant.com.domain.model;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by lucas on 28/04/2017.
 */

public class Job {
    private String id;
    private String description;
    private String prix;
    private String adresse;
    private String date;
    private String heure;
    private String utilisateurId;
    private User utilisateur;

    public Job(String id, String description, String prix, String adresse, String date, String heure, String utilisateurId, User utilisateur) {
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.utilisateurId = utilisateurId;
        this.utilisateur = utilisateur;
    }

    public Job(String description, String prix, String adresse, String date, String heure, String utilisateurId) {
        this.description = description;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.utilisateurId = utilisateurId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
