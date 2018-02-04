package com.studiant.com.storage.network.services;

import com.studiant.com.storage.network.model.RESTCard;
import com.studiant.com.storage.network.model.RESTCardReg;
import com.studiant.com.storage.network.model.RESTIban;
import com.studiant.com.storage.network.model.RESTImage;
import com.studiant.com.storage.network.model.RESTJob;
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
import retrofit2.http.PATCH;
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
                                     @Field("telephoneUtilisateur") String telephoneUtilisateur,
                                     @Field("passwordUtilisateur") String passwordUtilisateur);


    @FormUrlEncoded
    @Headers("Connection: close")
    @PATCH("/api/Utilisateurs")
    Call<RESTUtilisateur> updateUser(@Field("descriptionUtilisateur") String descriptionUtilisateur,
                                     @Field("telephoneUtilisateur") String telephoneUtilisateur,
                                     @Field("diplomeUtilisateur") String diplomeUtilisateur,
                                     @Field("id") String id);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/user_create_iban.php")
    Call<RESTIban> insertRib( @Field("idMangoPayUtilisateur") String idMangoPayUtilisateur,
                                     @Field("IBAN") String iban,
                                     @Field("BIC") String bic,
                                     @Field("nameUtilisateur") String nameUtilisateur,
                                     @Field("AddressLine1") String addresseLine,
                                     @Field("city") String city,
                                     @Field("codePostal") String codePostal);


    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/payout.php")
    Call<Void> getMoney(@Field("idWalletUtilisateur") String idWalletUtilisateur,
                         @Field("nomUtilisateur") String nomUtilsateur,
                         @Field("prenomUtilisateur") String prenomUtilisateur,
                         @Field("idMangoPayUtilisateur") String idMangoPayUtilisateur,
                         @Field("mailUtilisateur") String mailUtilisateur,
                         @Field("ibanUtilisateur") String ibanUtilisateur,
                         @Field("idIbanUtilisateur") String idIbanUtilisateur);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/studiantApi/forgotPassword.php")
    Call<Void> getPassword(@Field("mailUtilisateur") String mailUtilisateur);

    /*@Headers("Connection: close")
    @POST("/api/Utilisateurs")
    Call<RESTUtilisateur> insertUser(@Body RESTUtilisateur user);*/

    @Headers("Connection: close")
    @GET("/api/Utilisateurs/findOne")
    Call<RESTUtilisateur> loginUser(@Query("filter") String query);

    @Headers("Connection: close")
    @GET("/api/Utilisateurs/findOne")
    Call<RESTUtilisateur> getUserFromConnectedProfile(@Query("filter") String query);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/user_get_card.php")
    Call<RESTCard> getUserCard(@Field("idMangoPayUtilisateur") String idMangoPay);

    @FormUrlEncoded
    @Headers("Connection: close")
    @POST("/mangoApi/demos/validateStudiantCode.php")
    Call<RESTJob> getPaiement(@Field("postulantId") String postulantId,
                              @Field("jobId") String jobId,
                              @Field("secret") String secret);

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
