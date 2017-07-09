package com.studiant.com.storage.network.services;

import com.studiant.com.storage.network.model.RESTJob;
import com.studiant.com.storage.network.model.RESTPostulant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/05/2017.
 */

public interface PostulantService {

    //Ajout d'un job avec l'utilisateur associé
    @Headers("Connection: close")
    @POST("/api/Postulants")
    Call<Void> insertPostulant(@Body RESTPostulant restPostulant);

    //Récupération de tous les postulant par job
    @Headers("Connection: close")
    @GET("/api/Jobs")
    Call<ArrayList<RESTJob>> getJobs(@Query("filter") String query);

    //Récupération de tous les postulant par job
    @Headers("Connection: close")
    @POST("/api/Postulants/update")
    Call<ArrayList<RESTJob>> updatePostulant(@Query("where") String query, @Body RESTPostulant restPostulant);

}
