package com.studiant.com.network.services;

import com.studiant.com.domain.model.User;
import com.studiant.com.network.model.RESTUtilisateur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/05/2017.
 */

public interface UtilisateurService {


    @Headers("Connection: close")
    @GET("/api/Utilisateurs/findOne")
    Call<RESTUtilisateur> uploadData();

    @Headers("Connection: close")
    @POST("/api/Utilisateurs")
    Call<Void> insertUser(@Body RESTUtilisateur user);

    @Headers("Connection: close")
    @GET("/api/Utilisateurs/findOne")
    Call<RESTUtilisateur> getUserFromConnectedProfile(@Query("filter") String query);
}