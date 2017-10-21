package com.studiant.com.domain.model;

/**
 * Created by groupama on 28/09/2017.
 */

public class PayIn {

    private String Status;
    private String creationDate;
    private String creditedWalletId;
    private String authorId;

    public PayIn(String status, String creationDate, String creditedWalletId, String authorId) {
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
