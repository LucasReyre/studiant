package com.studiant.com.storage.impl;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.network.RestClient;
import com.studiant.com.network.converters.RESTModelConverter;
import com.studiant.com.network.model.RESTUtilisateur;
import com.studiant.com.network.services.UtilisateurService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class UserRepositoryImpl implements UserRepository {

    private User user;

    @Override
    public User insertUser(User user) {

        RESTUtilisateur restUtilisateur = null;
        UtilisateurService utilisateurService = RestClient.getService(UtilisateurService.class);

        //    utilisateurService.uploadData().enqueue(this);
        try {
            Response<RESTUtilisateur> response = utilisateurService.insertUser(RESTModelConverter.convertToRestUserModel(user)).execute();

            restUtilisateur = response.body();
            Timber.i("UPLOAD SUCCESS: %d", response.code());
            Log.d("response", "finish + " + restUtilisateur.getmNomUtilisateur());

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }

        return RESTModelConverter.convertToUserModel(restUtilisateur);

    }

    @Override
    public User getConnectedProfile() {

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
    public User getProfileFromConnectedUser() {

        User user = getConnectedProfile();
        RESTUtilisateur restUtilisateur = null;

        if (user == null){
            return null;
        }else {

            UtilisateurService utilisateurService = RestClient.getService(UtilisateurService.class);

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
