package com.studiant.com.storage.network.services;

import com.studiant.com.AndroidApplication;
import com.studiant.com.storage.network.model.RESTJob;
import com.studiant.com.storage.network.model.RESTPostulant;

import java.util.ArrayList;

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
        @GET("/api/Utilisateurs/{user_id}/creer?filter[include][utilisateurs]")
    Call<ArrayList<RESTJob>> getJobsByUser(@Path(value = "user_id", encoded = true) String userId);

    //Récupération de tous les jobs crées par un utilisateur
    @Headers("Connection: close")
    @GET("/api/Jobs")
    Call<ArrayList<RESTJob>> getHistoriqueJobsByUser(@Query("filter") String query);

    //Récupération de tous les jobs sans distinction avec les utilisateurs associés
    @Headers("Connection: close")
    @GET("/api/Jobs?filter[include][appartenir]&filter[where][statutJob]=0")
    Call<ArrayList<RESTJob>> getJobs();

    //Récupération de tous les jobs sans distinction avec les utilisateurs associés
    /*@GET("/api/Jobs?filter[include][utilisateurs]&filter[where][utilisateurId]={user_id}")
    Call<ArrayList<RESTJob>> getJobsByUser(@Path(value = "user_id", encoded = true) String userId);*/

    //Update d'un job
    @Headers("Connection: close")
    @POST("/api/Jobs/")
    Call<ArrayList<RESTJob>> updateJob(@Query("update") String query, @Body RESTJob restJob);

}
