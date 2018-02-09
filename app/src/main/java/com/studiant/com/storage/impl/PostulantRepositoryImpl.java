package com.studiant.com.storage.impl;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.storage.network.RestClient;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.storage.network.converters.RESTModelConverter;
import com.studiant.com.storage.network.model.RESTJob;
import com.studiant.com.storage.network.model.RESTPostulant;
import com.studiant.com.storage.network.services.JobService;
import com.studiant.com.storage.network.services.PostulantService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import timber.log.Timber;

import static com.studiant.com.storage.Constants.HTTP_CODE_200;
import static com.studiant.com.storage.Constants.HTTP_CODE_500;
import static com.studiant.com.storage.Constants.REST_API_URL;

/**
 * Created by dmilicic on 1/29/16.
 */
public class PostulantRepositoryImpl implements PostulantRepository {


    @Override
    public void insertPostulant(Postulant postulant) throws WSException {
        RESTPostulant restPostulant = null;
        PostulantService postulantService = RestClient.createService(PostulantService.class, REST_API_URL);

        try {

            Response<Void> response = postulantService.insertPostulant(RESTModelConverter.convertToRestPostulantModel(postulant)).execute();

            if (response.code() == HTTP_CODE_200)
                Timber.i("UPLOAD SUCCESS: %d", response.code());
            else if (response.code() == HTTP_CODE_500)
                throw new WSException(response.errorBody().string());
            //restJob = response.body();

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }
    }

    @Override
    public void choosePostulant(User user, Job job) {
    }

    @Override
    public Postulant findPostulant(String jobId, String utilisateurId) {
        PostulantService postulantService = RestClient.createService(PostulantService.class, REST_API_URL);
        System.out.println("findPostulant");

        String query = "{\"where\":{\"jobId\":\""+jobId+"\", \"utilisateurId\":\""+utilisateurId+"\"}";

        try {
            //String query = "{\"include\":[\"appartenir\"]}";
            Response<RESTPostulant> response = postulantService.findPostulant(query).execute();

            //restJob = response.body();
            Timber.i("GET ALL JOBS POSTULANT: %d", response.code());

            return RESTModelConverter.convertToPostulantModel(response.body());

        } catch (IOException e) { // something went wrong
            Timber.e("GET JOBS BY USER FAILED"+e.getMessage());
        }

        return null;
    }
}
