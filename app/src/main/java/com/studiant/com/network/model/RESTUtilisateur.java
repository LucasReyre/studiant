package com.studiant.com.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTUtilisateur {

    @SerializedName("id")
    private String mId;

    @SerializedName("nomUtilisateur")
    private String mNomUtilisateur;

    @SerializedName("prenomUtilisateur")
    private String mPrenomUtilisateur;

    @SerializedName("dateNaissanceUtilisateur")
    private String mDateNaissanceUtilisateur;

    @SerializedName("photoUtilisateur")
    private String mPhotoUtilisateur;

    @SerializedName("mailUtilisateur")
    private String mMailUtilisateur;

    @SerializedName("typeUtilisateur")
    private int mTypeUtilisateur;

    @SerializedName("idExterneUtilisateur")
    private String mIdExterneUtilisateur;

    @SerializedName("typeConnexionUtilisateur")
    private String mTypeConnexionUtilisateur;

    @SerializedName("descriptionUtilisateur")
    private String mDescriptionUtilisateur;

    @SerializedName("diplomeUtilisateur")
    private String mDiplomeUtilisateur;

    @SerializedName("permisUtilisateur")
    private Boolean mPermisUtilisateur;

    public RESTUtilisateur(String mId, String mNomUtilisateur, String mPrenomUtilisateur, String mDateNaissanceUtilisateur, String mPhotoUtilisateur, String mMailUtilisateur, int mTypeUtilisateur, String mIdExterneUtilisateur, String mTypeConnexionUtilisateur, String mDescriptionUtilisateur, String mDiplomeUtilisateur, Boolean mPermisUtilisateur) {
        this.mId = mId;
        this.mNomUtilisateur = mNomUtilisateur;
        this.mPrenomUtilisateur = mPrenomUtilisateur;
        this.mDateNaissanceUtilisateur = mDateNaissanceUtilisateur;
        this.mPhotoUtilisateur = mPhotoUtilisateur;
        this.mMailUtilisateur = mMailUtilisateur;
        this.mTypeUtilisateur = mTypeUtilisateur;
        this.mIdExterneUtilisateur = mIdExterneUtilisateur;
        this.mTypeConnexionUtilisateur = mTypeConnexionUtilisateur;
        this.mDescriptionUtilisateur = mDescriptionUtilisateur;
        this.mDiplomeUtilisateur = mDiplomeUtilisateur;
        this.mPermisUtilisateur = mPermisUtilisateur;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNomUtilisateur() {
        return mNomUtilisateur;
    }

    public void setmNomUtilisateur(String mNomUtilisateur) {
        this.mNomUtilisateur = mNomUtilisateur;
    }

    public String getmPrenomUtilisateur() {
        return mPrenomUtilisateur;
    }

    public void setmPrenomUtilisateur(String mPrenomUtilisateur) {
        this.mPrenomUtilisateur = mPrenomUtilisateur;
    }

    public String getmDateNaissanceUtilisateur() {
        return mDateNaissanceUtilisateur;
    }

    public void setmDateNaissanceUtilisateur(String mDateNaissanceUtilisateur) {
        this.mDateNaissanceUtilisateur = mDateNaissanceUtilisateur;
    }

    public String getmPhotoUtilisateur() {
        return mPhotoUtilisateur;
    }

    public void setmPhotoUtilisateur(String mPhotoUtilisateur) {
        this.mPhotoUtilisateur = mPhotoUtilisateur;
    }

    public String getmMailUtilisateur() {
        return mMailUtilisateur;
    }

    public void setmMailUtilisateur(String mMailUtilisateur) {
        this.mMailUtilisateur = mMailUtilisateur;
    }

    public int getmTypeUtilisateur() {
        return mTypeUtilisateur;
    }

    public void setmTypeUtilisateur(int mTypeUtilisateur) {
        this.mTypeUtilisateur = mTypeUtilisateur;
    }

    public String getmIdExterneUtilisateur() {
        return mIdExterneUtilisateur;
    }

    public void setmIdExterneUtilisateur(String mIdExterneUtilisateur) {
        this.mIdExterneUtilisateur = mIdExterneUtilisateur;
    }

    public String getmTypeConnexionUtilisateur() {
        return mTypeConnexionUtilisateur;
    }

    public void setmTypeConnexionUtilisateur(String mTypeConnexionUtilisateur) {
        this.mTypeConnexionUtilisateur = mTypeConnexionUtilisateur;
    }

    public String getmDescriptionUtilisateur() {
        return mDescriptionUtilisateur;
    }

    public void setmDescriptionUtilisateur(String mDescriptionUtilisateur) {
        this.mDescriptionUtilisateur = mDescriptionUtilisateur;
    }

    public String getmDiplomeUtilisateur() {
        return mDiplomeUtilisateur;
    }

    public void setmDiplomeUtilisateur(String mDiplomeUtilisateur) {
        this.mDiplomeUtilisateur = mDiplomeUtilisateur;
    }

    public Boolean getmPermisUtilisateur() {
        return mPermisUtilisateur;
    }

    public void setmPermisUtilisateur(Boolean mPermisUtilisateur) {
        this.mPermisUtilisateur = mPermisUtilisateur;
    }
}
