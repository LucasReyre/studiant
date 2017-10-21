package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTCardReg {

    @SerializedName("AccessKey")
    private String accessKey;

    @SerializedName("BaseUrl")
    private String baseUrl;

    @SerializedName("Id")
    private String cardPreregistrationId;

    @SerializedName("CardRegistrationURL")
    private String cardRegistrationUrl;

    @SerializedName("CardType")
    private String cardType;

    @SerializedName("ClientId")
    private String  clientId;

    @SerializedName("PreregistrationData")
    private String preregistrationData;

    public RESTCardReg(String accessKey, String baseUrl, String cardPreregistrationId, String cardRegistrationUrl, String cardType, String clientId, String preregistrationData) {
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
