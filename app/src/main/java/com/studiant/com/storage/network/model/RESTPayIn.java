package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTPayIn {

    @SerializedName("Status")
    private String Status;

    @SerializedName("CreationDate")
    private String creationDate;

    @SerializedName("CreditedWalletId")
    private String creditedWalletId;

    @SerializedName("AuthorId")
    private String authorId;

    public RESTPayIn(String status, String creationDate, String creditedWalletId, String authorId) {
        Status = status;
        this.creationDate = creationDate;
        this.creditedWalletId = creditedWalletId;
        this.authorId = authorId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreditedWalletId() {
        return creditedWalletId;
    }

    public void setCreditedWalletId(String creditedWalletId) {
        this.creditedWalletId = creditedWalletId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
