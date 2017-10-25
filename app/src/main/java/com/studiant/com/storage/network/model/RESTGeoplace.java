package com.studiant.com.storage.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lucas on 11/05/2017.
 */

public class RESTGeoplace {

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;


    public RESTGeoplace() {
    }

    public RESTGeoplace(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
