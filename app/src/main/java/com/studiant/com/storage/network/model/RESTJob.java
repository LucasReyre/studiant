package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTJob {

    @SerializedName("id")
    private String mId;

    @SerializedName("descriptionJob")
    private String mDescriptionJob;

    @SerializedName("prixJob")
    private String mPrixJob;

    @SerializedName("adresseJob")
    private String mAdresseJob;

    @SerializedName("cityJob")
    private String mCityJob;

    @SerializedName("categorieJob")
    private String mcategorieJob;

    @SerializedName("statutJob")
    private String mStatutJob;

    @SerializedName("dateJob")
    private String mDateJob;

    @SerializedName("heureJob")
    private String mHeureJob;

    @SerializedName("utilisateurId")
    private String mUtilisateurId;

    @SerializedName("postulantId")
    private String mPostulantId;

    @SerializedName("typePaiementJob")
    private String typePaiementJob;

    @SerializedName("latlongJob")
    private RESTGeoplace mGeoplace;

    @SerializedName("utilisateurs")
    private ArrayList<RESTUtilisateur> mRestPostulants;

    @SerializedName("appartenir")
    private RESTUtilisateur mRestUtilisateur;


    public RESTJob(String mDescriptionJob, String mPrixJob, String mAdresseJob, String mDateJob, String mHeureJob, String mUtilisateurId, String mStatutJob, String typePaiementJob) {
        this.mDescriptionJob = mDescriptionJob;
        this.mPrixJob = mPrixJob;
        this.mAdresseJob = mAdresseJob;
        this.mDateJob = mDateJob;
        this.mHeureJob = mHeureJob;
        this.mUtilisateurId = mUtilisateurId;
        this.mStatutJob = mStatutJob;
        this.typePaiementJob = typePaiementJob;
    }


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmDescriptionJob() {
        return mDescriptionJob;
    }

    public void setmDescriptionJob(String mDescriptionJob) {
        this.mDescriptionJob = mDescriptionJob;
    }

    public String getmPrixJob() {
        return mPrixJob;
    }

    public void setmPrixJob(String mPrixJob) {
        this.mPrixJob = mPrixJob;
    }

    public String getmAdresseJob() {
        return mAdresseJob;
    }

    public void setmAdresseJob(String mAdresseJob) {
        this.mAdresseJob = mAdresseJob;
    }

    public String getmDateJob() {
        return mDateJob;
    }

    public void setmDateJob(String mDateJob) {
        this.mDateJob = mDateJob;
    }

    public String getmHeureJob() {
        return mHeureJob;
    }

    public void setmHeureJob(String mHeureJob) {
        this.mHeureJob = mHeureJob;
    }

    public String getmUtilisateurId() {
        return mUtilisateurId;
    }

    public void setmUtilisateurId(String mUtilisateurId) {
        this.mUtilisateurId = mUtilisateurId;
    }

    public RESTUtilisateur getmRestUtilisateur() {
        return mRestUtilisateur;
    }

    public void setmRestUtilisateur(RESTUtilisateur mRestUtilisateur) {
        this.mRestUtilisateur = mRestUtilisateur;
    }

    public ArrayList<RESTUtilisateur> getmRestPostulant() {
        return mRestPostulants;
    }

    public void setmRestPostulant(ArrayList<RESTUtilisateur> mRestPostulant) {
        this.mRestPostulants = mRestPostulant;
    }

    public String getmStatutJob() {
        return mStatutJob;
    }

    public void setmStatutJob(String mStatutJob) {
        this.mStatutJob = mStatutJob;
    }

    public ArrayList<RESTUtilisateur> getmRestPostulants() {
        return mRestPostulants;
    }

    public void setmRestPostulants(ArrayList<RESTUtilisateur> mRestPostulants) {
        this.mRestPostulants = mRestPostulants;
    }

    public String getmPostulantId() {
        return mPostulantId;
    }

    public void setmPostulantId(String mPostulantId) {
        this.mPostulantId = mPostulantId;
    }

    public String getTypePaiementJob() {
        return typePaiementJob;
    }

    public void setTypePaiementJob(String typePaiementJob) {
        this.typePaiementJob = typePaiementJob;
    }

    public RESTGeoplace getmGeoplace() {
        return mGeoplace;
    }

    public void setmGeoplace(RESTGeoplace mGeoplace) {
        this.mGeoplace = mGeoplace;
    }

    public String getmCityJob() {
        return mCityJob;
    }

    public void setmCityJob(String mCityJob) {
        this.mCityJob = mCityJob;
    }

    public String getMcategorieJob() {
        return mcategorieJob;
    }

    public void setMcategorieJob(String mcategorieJob) {
        this.mcategorieJob = mcategorieJob;
    }
}
