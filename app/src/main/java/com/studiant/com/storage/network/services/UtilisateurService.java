package com.studiant.com.storage.network.services;

import com.studiant.com.storage.network.model.RESTCard;
import com.studiant.com.storage.network.model.RESTCardReg;
import com.studiant.com.storage.network.model.RESTImage;
import com.studiant.com.storage.network.model.RESTPayIn;
import com.studiant.com.storage.network.model.RESTUtilisateur;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/users_create.php")
    Call<RESTUtilisateur> insertUser(@Field(value = "mailUtilisateur", encoded = true) String mailUtilisateur,
                                     @Field("prenomUtilisateur") String prenomUtilisateur,
                                     @Field("nomUtilisateur") String nomUtilisateur,
                                     @Field("typeUtilisateur") String typeUtilisateur,
                                     @Field("firebaseToken") String firebaseToken,
                                     @Field("typeConnexionUtilisateur") String typeConnexionUtilisateur,
                                     @Field("diplomeUtilisateur") String diplomeUtilisateur,
                                     @Field("photoUtilisateur") String photoUtilisateur,
                                     @Field("idExterneUtilisateur") String idExterneUtilisateur,
                                     @Field("telephoneUtilisateur") String telephoneUtilisateur);

    /*@Headers("Connection: close")
    @POST("/api/Utilisateurs")
    Call<RESTUtilisateur> insertUser(@Body RESTUtilisateur user);*/

    @Headers("Connection: close")
    @GET("/api/Utilisateurs/findOne")
    Call<RESTUtilisateur> getUserFromConnectedProfile(@Query("filter") String query);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/user_get_card.php")
    Call<RESTCard> getUserCard(@Field("idMangoPayUtilisateur") String idMangoPay);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/card_reg.php")
    Call<RESTCardReg> getUserCardReg(@Field("idMangoPayUtilisateur") String idMangoPay);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/user_direct_pay_in.php")
    Call<RESTPayIn> directPayment(@Field("idMangoPayUtilisateur") String idMangoPay, @Field("amount") String amount);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/studiantApi/import.php")
    Call<RESTImage> uploadImage(@Field("picture") String image, @Field("secret") String secret);

}
