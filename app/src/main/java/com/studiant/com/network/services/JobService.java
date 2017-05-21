package com.studiant.com.network.services;

import com.studiant.com.network.model.RESTJob;
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

public interface JobService {

    @Headers("Connection: close")
    @POST("/api/Jobs")
    Call<Void> insertJob(@Body RESTJob job);

}