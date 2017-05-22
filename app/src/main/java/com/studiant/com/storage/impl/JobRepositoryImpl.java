package com.studiant.com.storage.impl;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.network.RestClient;
import com.studiant.com.network.converters.RESTModelConverter;
import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTUtilisateur;
import com.studiant.com.network.services.JobService;
import com.studiant.com.network.services.UtilisateurService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class JobRepositoryImpl implements JobRepository {

    @Override
    public void insertJob(Job job) {
        RESTJob restJob = null;
        JobService jobService = RestClient.getService(JobService.class);

        try {
            Response<Void> response = jobService.insertJob(RESTModelConverter.convertToRestJobModel(job)).execute();

            //restJob = response.body();
            Timber.i("UPLOAD SUCCESS: %d", response.code());

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }

    }
}
