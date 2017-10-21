package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTImage {

    @SerializedName("photoUtilisateur")
    private String urlPicture;

    public RESTImage(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
