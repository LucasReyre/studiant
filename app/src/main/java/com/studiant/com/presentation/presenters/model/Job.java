package com.studiant.com.presentation.presenters.model;


import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lucas on 28/04/2017.
 */

public class Job implements Serializable{
    private String id;
    private String description;
    private String categorie;
    private String city;
    private String prix;
    private String adresse;
    private String date;
    private String heure;
    private String lat;
    private String lng;
    private String utilisateurId;
    private User utilisateur;
    private String modePaiement;
    private String statut;
    private ArrayList<User> postulants;

    private View.OnClickListener requestBtnClickListener;
    private View.OnClickListener studiantCodeBtnClickListener;

    public Job(){}

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

    public Job(String id, String description, String prix, String adresse, String date, String heure, String utilisateurId) {
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.utilisateurId = utilisateurId;
    }

    public Job(String id, String description, String prix, String adresse, String date, String heure, String utilisateurId, User utilisateur ,ArrayList<User> postulants) {
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.utilisateurId = utilisateurId;
        this.utilisateur = utilisateur;
        this.postulants = postulants;
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

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public View.OnClickListener getStudiantCodeBtnClickListener() {
        return studiantCodeBtnClickListener;
    }

    public void setStudiantCodeBtnClickListener(View.OnClickListener studiantCodeBtnClickListener) {
        this.studiantCodeBtnClickListener = studiantCodeBtnClickListener;
    }

    public ArrayList<User> getPostulants() {
        return postulants;
    }

    public void setPostulants(ArrayList<User> postulant) {
        this.postulants = postulant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
