package com.studiant.com.domain.model;

/**
 * Created by groupama on 28/09/2017.
 */

public class Card {

    private String expirationDate;
    private String alias;
    private String cardType;
    private String cardProvider;
    private String country;
    private String  validity;
    private String userId;
    private String id;

    public Card(String expirationDate, String alias, String cardType, String cardProvider, String country, String validity, String userId, String id) {
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
