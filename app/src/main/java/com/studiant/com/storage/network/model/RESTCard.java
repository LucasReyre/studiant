package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTCard {

    @SerializedName("ExpirationDate")
    private String expirationDate;

    @SerializedName("Alias")
    private String alias;

    @SerializedName("CardType")
    private String cardType;

    @SerializedName("CardProvider")
    private String cardProvider;

    @SerializedName("Country")
    private String country;

    @SerializedName("Validity")
    private String  validity;

    @SerializedName("UserId")
    private String userId;

    @SerializedName("Id")
    private String id;

    public RESTCard(String expirationDate, String alias, String cardType, String cardProvider, String country, String validity, String userId, String id) {
        this.expirationDate = expirationDate;
        this.alias = alias;
        this.cardType = cardType;
        this.cardProvider = cardProvider;
        this.country = country;
        this.validity = validity;
        this.userId = userId;
        this.id = id;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
