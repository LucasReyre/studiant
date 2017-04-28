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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dmilicic on 1/29/16.
 */
public class UserRepositoryImpl implements UserRepository {

    private User user;

    @Override
    public void insert(User user) {

    }

    @Override
    public User getConnectedProfile() {


        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                final JSONObject json = response.getJSONObject();
                Profile profile = Profile.getCurrentProfile();
                try {
                    if(json != null){

                        /*Log.e("lucas", json.getString("name"));
                        Log.e("lucas", json.getString("email"));
                        Log.e("lucas", json.getString("id"));*/

                        user = new User(profile.getName(),profile.getLastName(),json.getString("email"), profile.getId(), profile.getProfilePictureUri(100,100));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAndWait();
        //request.executeAsync();

        return user;
    }

}
