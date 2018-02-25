package com.studiant.com.domain.model;

/**
 * Created by groupama on 28/09/2017.
 */

public class Payout {

    private String id;
    private String status;
    private String amount;

    public Payout(String id, String status, String amount) {
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
