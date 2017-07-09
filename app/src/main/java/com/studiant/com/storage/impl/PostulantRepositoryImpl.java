package com.studiant.com.storage.impl;

import android.util.Log;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.storage.network.RestClient;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.storage.network.converters.RESTModelConverter;
import com.studiant.com.storage.network.model.RESTPostulant;
import com.studiant.com.storage.network.services.PostulantService;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

import static com.studiant.com.storage.Constants.HTTP_CODE_200;
import static com.studiant.com.storage.Constants.HTTP_CODE_500;

/**
 * Created by dmilicic on 1/29/16.
 */
public class PostulantRepositoryImpl implements PostulantRepository {


    @Override
    public void insertPostulant(Postulant postulant) throws WSException {
        RESTPostulant restPostulant = null;
        PostulantService postulantService = RestClient.getService(PostulantService.class);

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
}
