package com.studiant.com.network.services;

import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTPostulant;
import com.studiant.com.network.model.RESTUtilisateur;

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

public interface PostulantService {

    //Ajout d'un job avec l'utilisateur associé
    @Headers("Connection: close")
    @POST("/api/Postulants")
    Call<Void> insertPostulant(@Body RESTPostulant restPostulant);

}
