package com.studiant.com.storage.impl;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.network.RestClient;
import com.studiant.com.network.converters.RESTModelConverter;
import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTPostulant;
import com.studiant.com.network.services.JobService;
import com.studiant.com.network.services.PostulantService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class PostulantRepositoryImpl implements PostulantRepository {


    @Override
    public void insertPostulant(Postulant postulant) {
        RESTPostulant restPostulant = null;
        PostulantService postulantService = RestClient.getService(PostulantService.class);

        try {

            Response<Void> response = postulantService.insertPostulant(RESTModelConverter.convertToRestPostulantModel(postulant)).execute();

            //restJob = response.body();
            Timber.i("UPLOAD SUCCESS: %d", response.code());

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }
    }

    @Override
    public void choosePostulant(User user, Job job) {

    }
}
