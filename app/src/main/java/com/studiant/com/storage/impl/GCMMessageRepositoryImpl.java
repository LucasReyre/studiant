package com.studiant.com.storage.impl;

import android.util.Log;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.GCMMessageRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.network.RestClient;
import com.studiant.com.network.converters.RESTModelConverter;
import com.studiant.com.network.model.RESTGCMMessage;
import com.studiant.com.network.model.RESTJob;
import com.studiant.com.network.model.RESTNotification;
import com.studiant.com.network.services.GCMMessageService;
import com.studiant.com.network.services.JobService;
import com.studiant.com.storage.Constants;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class GCMMessageRepositoryImpl implements GCMMessageRepository {


    @Override
    public void sendNotification(Job job) {
        Log.d("sendNotification", "begin");
        Log.d("sendNotification", "user token : "+job.getUtilisateur().getFirebaseToken());
        //String to = "cROhkQZ-oiw:APA91bEs4xCwYOT8pK8E3v1CTYFF25BT7p3Xk_8jzYTz4WFrTU5ml8nFpE7I_0huyr6-zA0zj4g8Igm0SBhSWHe7ccuWbr4QpD8gn2C1zCuU3JW0PYAcbvPuxjebSuA63mbTyXuoH70r";
        String to = job.getUtilisateur().getFirebaseToken();

        RESTNotification restNotification = new RESTNotification(Constants.GCM_TITLE, Constants.GCM_BODY_NEW_POSTULANT+"\""+job.getDescription().substring(0,10)+"\"");
        RESTGCMMessage restgcmMessage = new RESTGCMMessage(to,restNotification);

        RestClient.changeApiBaseUrl(Constants.HTTP_URL_FCM);
        GCMMessageService gcmMessageService = RestClient.getService(GCMMessageService.class);
        RestClient.changeApiBaseUrl("https://loopbackstudiant.herokuapp.com/");

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "key="+ Constants.LEGACY_SERVER_KEY);

        try {
            Response<Void> response = gcmMessageService.sendNotification(map,restgcmMessage).execute();
            Log.d("sendNotification", " - "+response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
