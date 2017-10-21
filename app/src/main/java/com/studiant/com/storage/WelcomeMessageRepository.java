package com.studiant.com.storage;

import android.util.Log;

import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.MessageRepository;
import com.studiant.com.storage.network.RestClient;
import com.studiant.com.storage.network.model.RESTUtilisateur;
import com.studiant.com.storage.network.services.UtilisateurService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.studiant.com.storage.Constants.REST_API_URL;

/**
 * Created by dmilicic on 1/29/16.
 */
public class WelcomeMessageRepository implements MessageRepository, Callback<User>  {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly

        UtilisateurService utilisateurService = RestClient.createService(UtilisateurService.class, REST_API_URL);

        //    utilisateurService.uploadData().enqueue(this);
        try {
            Response<RESTUtilisateur> response = utilisateurService.uploadData().execute();
            Log.d("response",""+response.body().getmNomUtilisateur());

            Timber.i("UPLOAD SUCCESS: %d", response.code());
            Log.d("response", "finish");


        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }

        return msg;
    }

    //Uniquement pour enqueue (appel asynchrone)
    @Override
    public void onResponse(Call<User> call, Response<User> response) {

        Log.d("response", "onResponse");
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.d("response", "onFailure"+t.getMessage());

    }
}
