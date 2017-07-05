package com.studiant.com.network.services;

import com.studiant.com.network.model.RESTGCMMessage;
import com.studiant.com.network.model.RESTUtilisateur;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/05/2017.
 */

public interface GCMMessageService {

    @Headers("Connection: close")
    @POST("fcm/send")
    Call<Void> sendNotification(@HeaderMap Map<String, String> headers, @Body RESTGCMMessage restgcmMessage);

}
