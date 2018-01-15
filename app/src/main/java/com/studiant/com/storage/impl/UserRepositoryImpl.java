package com.studiant.com.storage.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.google.gson.Gson;
import com.studiant.com.AndroidApplication;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.model.PayIn;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.storage.network.RestClient;
import com.studiant.com.storage.network.converters.RESTModelConverter;
import com.studiant.com.storage.network.model.RESTCard;
import com.studiant.com.storage.network.model.RESTCardReg;
import com.studiant.com.storage.network.model.RESTImage;
import com.studiant.com.storage.network.model.RESTPayIn;
import com.studiant.com.storage.network.model.RESTUtilisateur;
import com.studiant.com.storage.network.services.UtilisateurService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

import static com.studiant.com.storage.Constants.REST_API_OVH;
import static com.studiant.com.storage.Constants.REST_API_URL;
import static com.studiant.com.storage.Constants.SHARED_PREFERENCE_NAME;
import static com.studiant.com.storage.Constants.SHARED_PREFERENCE_USER;

public class UserRepositoryImpl implements UserRepository {

    private User user;

    @Override
    public User insertUser(User user) throws Exception {
        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_OVH);

        System.out.println("insert user : "+user.getFirstName());
        System.out.println("insert user : "+user.getLastName());
        /*try {
            System.out.println("code test");
            Response<RESTUtilisateur> response = utilisateurService.insertUser(RESTModelConverter.convertToRestUserModel(user)).execute();
            System.out.println("code "+response.code());
            restUtilisateur = response.body();
            Timber.i("UPLOAD SUCCESS: %d", response.code());
            Log.d("response", "finish + " + restUtilisateur.getmNomUtilisateur());

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }catch (Exception e){
            System.out.println("exception"+e.getMessage());
        }
*/
        try {

            //Response<RESTUtilisateur> response = utilisateurService.insertUser(RESTModelConverter.convertToRestUserModel(user)).execute();
            Response<RESTUtilisateur> response = utilisateurService.insertUser(user.getEmail(),user.getFirstName(),user.getLastName(),String.valueOf(user.getTypeUser()),user.getFirebaseToken(),String.valueOf(user.getTypeConnexion()),user.getDiplome(),user.getProfilePicture(),user.getIdExterne(), user.getTelephoneUtilisateur(),user.getPassword()).execute();
            if (response.code() == 200){
                System.out.println("ok" + response.body().getmId());
                return RESTModelConverter.convertToUserModel(response.body());
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
                throw new IOException(e);
        }


    }

    @Override
    public User loginUser(String mail, String password) throws Exception {
        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_URL);
        try {

            String query = "{\"where\":{\"mailUtilisateur\":\""+mail+"\", \"passwordUtilisateur\":\"" + password + "\"}}";
            String query2 = "{\"where\":{\"passwordUtilisateur\":\""+password+"\"}}";
            Response<RESTUtilisateur> response = utilisateurService.loginUser(query).execute();

            if (response.code() == 200){
                System.out.println("ok");
                return RESTModelConverter.convertToUserModel(response.body());
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    @Override
    public CardReg getCardReg(User user) throws Exception {
        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_OVH);
        System.out.println("mangopay "+user.getIdMangoPay());
        try {
            Response<RESTCardReg> response = utilisateurService.getUserCardReg(user.getIdMangoPay()).execute();
            if (response.code() == 200){
                System.out.println("ok");
                return RESTModelConverter.convertToCardRegModel(response.body());
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Card getCardUser(User user) throws Exception {
        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_OVH);

        try {
            Response<RESTCard> response = utilisateurService.getUserCard(user.getIdMangoPay()).execute();
            if (response.code() == 200){
                System.out.println("ok");
                return RESTModelConverter.convertToCardModel(response.body());
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    @Override
    public User getConnectedFacebookProfile() {

        if(AccessToken.getCurrentAccessToken() != null) {

            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {

                    final JSONObject json = response.getJSONObject();
                    Profile profile = Profile.getCurrentProfile();
                    try {
                        if (json != null) {

                            user = new User(profile.getFirstName(),
                                    profile.getLastName(),
                                    json.getString("email"),
                                    profile.getId(),
                                    profile.getProfilePictureUri(200, 200).toString(),
                                    json.getString("birthday"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,picture,birthday");
            request.setParameters(parameters);
            request.executeAndWait();
            //request.executeAsync();

            return user;
        }else {
            return null;
        }
    }

    @Override
    public User getConnectedNormalProfile() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = AndroidApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SHARED_PREFERENCE_USER, null);
        User user = gson.fromJson(json, User.class);

        System.out.println("connected : "+json);
        return user;
    }

    @Override
    public User getProfileFromConnectedUser() {

        User user = getConnectedNormalProfile();
        RESTUtilisateur restUtilisateur = null;

        if (user != null){
            Log.d("UserRepositoryImpl", "already connect");
            return user;
        }else {
            Log.d("UserRepositoryImpl", "never connect");
            user = getConnectedFacebookProfile();

            if (user == null){
                Log.d("UserRepositoryImpl", "null");
                return new User();
            }else {

                Log.d("UserRepositoryImpl", "not null");
                UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_URL);

                //    utilisateurService.uploadData().enqueue(this);
                try {

                    String query = "{\"where\":{\"mailUtilisateur\":\""+user.getEmail()+"\"}}";
                    Response<RESTUtilisateur> response = utilisateurService.getUserFromConnectedProfile(query).execute();
                    Timber.i("UPLOAD SUCCESS: %d", response.code());
                    Log.d("response", "finish");
                    restUtilisateur = response.body();

                } catch (IOException e) { // something went wrong
                    Timber.e("UPLOAD FAIL"+e.getMessage());
                }

                return RESTModelConverter.convertToUserModel(restUtilisateur);
            }
        }
    }

    @Override
    public String uploadImage(String image) throws Exception {

        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_OVH);
        System.out.println("upload image service");

        try {
            Response<RESTImage> response = utilisateurService.uploadImage(image, "cQEWS7UoI39I7Uk1FxC0YcuG8ge3kXEWArhu2DM1").execute();
        System.out.println("upload image service2");
            if (response.code() == 200){
                System.out.println("ok");
                return response.body().getUrlPicture();
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public PayIn directPayment(String mangoPayId, String amount) throws Exception {
        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_OVH);

        try {
            Response<RESTPayIn> response = utilisateurService.directPayment(mangoPayId, amount).execute();
            if (response.code() == 200){
                System.out.println("ok");
                return RESTModelConverter.convertToPayInModel(response.body());
            }
            else{
                System.out.println("error");
                throw new Exception(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
