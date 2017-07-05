package com.studiant.com.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTGCMMessage {

    @SerializedName("to")
    private String to;

    @SerializedName("notification")
    private RESTNotification notification;

    public RESTGCMMessage(String to, RESTNotification notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public RESTNotification getNotification() {
        return notification;
    }

    public void setNotification(RESTNotification notification) {
        this.notification = notification;
    }
}
