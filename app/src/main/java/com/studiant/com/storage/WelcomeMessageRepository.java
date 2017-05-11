package com.studiant.com.storage;

import android.util.Log;

import com.studiant.com.domain.repository.MessageRepository;
import com.studiant.com.network.RestClient;
import com.studiant.com.network.services.UtilisateurService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly


        UtilisateurService syncService = RestClient.getService(UtilisateurService.class);

        // TODO: get the real user's name

        // get all unsynced data

        // run the upload
        try {
            Log.d("response", "start");
            Response<Void> response = syncService.uploadData().execute();

            Timber.i("UPLOAD SUCCESS: %d", response.code());
            Log.d("response", "finish");


        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL");
        }


        return msg;
    }

    private Callback<Void> mResponseCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Timber.i("UPLOAD SUCCESS: %d", response.code());
            Log.d("response", "onResponse");

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Timber.e("UPLOAD FAIL");
            Log.d("response", "onResponse");

        }
    };
}
