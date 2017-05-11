package com.studiant.com.network.services;

import com.studiant.com.network.model.RESTUtilisateur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lucas on 11/05/2017.
 */

public interface UtilisateurService {

    /**
     * This endpoint will be used to send new costs created on this device.
     */
    @Headers("Connection: close")
    @GET("/api/Utilisateurs")
    Call<Void> uploadData();
   // Call<Void> uploadData(@Body RESTUtilisateur data);
}
