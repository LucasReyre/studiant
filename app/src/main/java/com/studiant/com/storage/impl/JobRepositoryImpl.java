package com.studiant.com.storage.impl;

import android.util.Log;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.storage.network.RestClient;
import com.studiant.com.storage.network.converters.RESTModelConverter;
import com.studiant.com.storage.network.model.RESTJob;
import com.studiant.com.storage.network.services.JobService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.studiant.com.storage.Constants.HTTP_CODE_200;
import static com.studiant.com.storage.Constants.REST_API_URL;
import static com.studiant.com.storage.Constants.STATUS_JOB_OPEN;

/**
 * Created by dmilicic on 1/29/16.
 */
public class JobRepositoryImpl implements JobRepository {

    @Override
    public void insertJob(Job job) {
        RESTJob restJob = null;
        job.setStatutJob(STATUS_JOB_OPEN);
        JobService jobService = RestClient.createService(JobService.class, REST_API_URL);

        try {

            Response<Void> response = jobService.insertJob(job.getUtilisateurId(), RESTModelConverter.convertToRestJobModel(job)).execute();

            //restJob = response.body();
            Timber.i("UPLOAD SUCCESS: %d", response.code());

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL"+e.getMessage());
        }

    }

    @Override
    public ArrayList<Job> getJobsByUser(User user) {

        JobService jobService = RestClient.createService(JobService.class, REST_API_URL);
        try {

            Response<ArrayList<RESTJob>> response = jobService.getJobsByUser(user.getId()).execute();
            //restJob = response.body();
            Timber.i("GET JOBS BY USER SUCCESS: %d", response.code());
            return RESTModelConverter.convertToArrayListJobModel(response.body());

        } catch (IOException e) { // something went wrong
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Job> getHistoriqueJobsByUser(User user) {
        JobService jobService = RestClient.createService(JobService.class, REST_API_URL);
        System.out.println("getHistoriqueJobsByUser");
        String query = "{\"where\":{\"postulantId\":\""+user.getId()+"\"},\"include\":[\"appartenir\"]}";

        try {
            //String query = "{\"include\":[\"appartenir\"]}";
            Response<ArrayList<RESTJob>> response = jobService.getHistoriqueJobsByUser(query).execute();

            //restJob = response.body();
            Timber.i("GET ALL JOBS SUCCESS: %d", response.code());

            return RESTModelConverter.convertToArrayListJobModel(response.body());

        } catch (IOException e) { // something went wrong
            Timber.e("GET JOBS BY USER FAILED"+e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Job> getJobs() {
        JobService jobService = RestClient.createService(JobService.class, REST_API_URL);

        try {
            //String query = "{\"include\":[\"appartenir\"]}";
            Response<ArrayList<RESTJob>> response = jobService.getJobs().execute();

            //restJob = response.body();
            Timber.i("GET ALL JOBS SUCCESS: %d", response.code());

            return RESTModelConverter.convertToArrayListJobModel(response.body());

        } catch (IOException e) { // something went wrong
            Timber.e("GET JOBS BY USER FAILED"+e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Job> updateJob(Job job) {
        JobService jobService = RestClient.createService(JobService.class, REST_API_URL);

        try {
            //String query = "{\"include\":[\"appartenir\"]}";
            String query = "[where][id]="+job.getId();
            System.out.println("update : "+job.getId());
            Response<ArrayList<RESTJob>> response = jobService.updateJob(job.getId(),RESTModelConverter.convertToRestJobModel(job)).execute();

            if(response.code() == HTTP_CODE_200)
                Timber.i("GET ALL JOBS SUCCESS: %d", response.code());
            else{
                Log.d("repo", "error");
                Log.d("repo", response.errorBody().string());

            }
            //restJob = response.body();

            return RESTModelConverter.convertToArrayListJobModel(response.body());

        } catch (IOException e) { // something went wrong
            Timber.e("GET JOBS BY USER FAILED"+e.getMessage());
        }
        return null;
    }
}
