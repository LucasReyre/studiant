package com.studiant.com.network.services;

import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTUtilisateur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/05/2017.
 */

public interface JobService {

    //Ajout d'un job avec l'utilisateur associé
    @Headers("Connection: close")
    @POST("/api/Utilisateurs/{user_id}/creer")
    Call<Void> insertJob(@Path(value = "user_id", encoded = true) String userId, @Body RESTJob job);


    //Récupération de tous les jobs crées par un utilisateur
    @Headers("Connection: close")
    @GET("/api/Utilisateurs/{user_id}/creer")
    Call<ArrayList<RESTJob>> getJobsByUser(@Path(value = "user_id", encoded = true) String userId);

    //Récupération de tous les jobs sans distinction avec les utilisateurs associés
    @Headers("Connection: close")
    @GET("/api/Jobs")
    Call<ArrayList<RESTJob>> getJobs(@Query("filter") String query);

}
