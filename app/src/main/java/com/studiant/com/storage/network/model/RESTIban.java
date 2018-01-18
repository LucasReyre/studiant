package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTIban {

    @SerializedName("Active")
    private String active;

    @SerializedName("CreationDate")
    private String creationDate;

    @SerializedName("OwnerName")
    private String ownerName;

    @SerializedName("UserId")
    private String userId;

    @SerializedName("Id")
    private String id;

    @SerializedName("Type")
    private String  type;


    public RESTIban(String active, String creationDate, String ownerName, String userId, String id, String type) {
        this.active = active;
        this.creationDate = creationDate;
        this.ownerName = ownerName;
        this.userId = userId;
        this.id = id;
        this.type = type;
    }


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
