package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTPayout {

    @SerializedName("Id")
    private String id;

    @SerializedName("Status")
    private String status;

    @SerializedName("Amount")
    private String amount;

    public RESTPayout(String id, String status, String amount) {
        this.id = id;
        this.status = status;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
