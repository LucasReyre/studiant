package com.studiant.com.domain.model;


/**
 * Created by groupama on 04/10/2017.
 */

public class CardReg {

    private String accessKey;
    private String baseUrl;
    private String cardPreregistrationId;
    private String cardRegistrationUrl;
    private String cardType;
    private String  clientId;
    private String preregistrationData;


    public CardReg(String accessKey, String baseUrl, String cardPreregistrationId, String cardRegistrationUrl, String cardType, String clientId, String preregistrationData) {
        this.accessKey = accessKey;
        this.baseUrl = baseUrl;
        this.cardPreregistrationId = cardPreregistrationId;
        this.cardRegistrationUrl = cardRegistrationUrl;
        this.cardType = cardType;
        this.clientId = clientId;
        this.preregistrationData = preregistrationData;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCardPreregistrationId() {
        return cardPreregistrationId;
    }

    public void setCardPreregistrationId(String cardPreregistrationId) {
        this.cardPreregistrationId = cardPreregistrationId;
    }

    public String getCardRegistrationUrl() {
        return cardRegistrationUrl;
    }

    public void setCardRegistrationUrl(String cardRegistrationUrl) {
        this.cardRegistrationUrl = cardRegistrationUrl;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPreregistrationData() {
        return preregistrationData;
    }

    public void setPreregistrationData(String preregistrationData) {
        this.preregistrationData = preregistrationData;
    }
}
