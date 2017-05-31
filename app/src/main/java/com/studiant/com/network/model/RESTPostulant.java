package com.studiant.com.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTPostulant {

    @SerializedName("id")
    private String mId;

    @SerializedName("timePostulant")
    private String mTimePostulant;

    @SerializedName("statutPostulant")
    private String mStatutPostulant;

    @SerializedName("jobId")
    private String mJobId;

    @SerializedName("utilisateurId")
    private String mUtilisateurId;


    public RESTPostulant(String mTimePostulant, String mStatutPostulant, String mJobId, String mUtilisateurId) {
        this.mTimePostulant = mTimePostulant;
        this.mStatutPostulant = mStatutPostulant;
        this.mJobId = mJobId;
        this.mUtilisateurId = mUtilisateurId;
    }

    public RESTPostulant(String mId, String mTimePostulant, String mStatutPostulant, String mJobId, String mUtilisateurId) {
        this.mId = mId;
        this.mTimePostulant = mTimePostulant;
        this.mStatutPostulant = mStatutPostulant;
        this.mJobId = mJobId;
        this.mUtilisateurId = mUtilisateurId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTimePostulant() {
        return mTimePostulant;
    }

    public void setmTimePostulant(String mTimePostulant) {
        this.mTimePostulant = mTimePostulant;
    }

    public String getmStatutPostulant() {
        return mStatutPostulant;
    }

    public void setmStatutPostulant(String mStatutPostulant) {
        this.mStatutPostulant = mStatutPostulant;
    }

    public String getmJobId() {
        return mJobId;
    }

    public void setmJobId(String mJobId) {
        this.mJobId = mJobId;
    }

    public String getmUtilisateurId() {
        return mUtilisateurId;
    }

    public void setmUtilisateurId(String mUtilisateurId) {
        this.mUtilisateurId = mUtilisateurId;
    }
}
