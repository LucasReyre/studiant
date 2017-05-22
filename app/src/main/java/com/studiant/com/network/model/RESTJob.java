package com.studiant.com.network.model;

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("dateJob")
    private String mDateJob;

    @SerializedName("heureJob")
    private String mHeureJob;

    @SerializedName("utilisateurId")
    private String mUtilisateurId;

    public RESTJob(String mId, String mDescriptionJob, String mPrixJob, String mAdresseJob, String mDateJob, String mHeureJob, String mUtilisateurId) {
        this.mId = mId;
        this.mDescriptionJob = mDescriptionJob;
        this.mPrixJob = mPrixJob;
        this.mAdresseJob = mAdresseJob;
        this.mDateJob = mDateJob;
        this.mHeureJob = mHeureJob;
        this.mUtilisateurId = mUtilisateurId;
    }

    public RESTJob(String mDescriptionJob, String mPrixJob, String mAdresseJob, String mDateJob, String mHeureJob, String mUtilisateurId) {
        this.mDescriptionJob = mDescriptionJob;
        this.mPrixJob = mPrixJob;
        this.mAdresseJob = mAdresseJob;
        this.mDateJob = mDateJob;
        this.mHeureJob = mHeureJob;
        this.mUtilisateurId = mUtilisateurId;
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
}
