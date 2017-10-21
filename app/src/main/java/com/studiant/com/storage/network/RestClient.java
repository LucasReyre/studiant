package com.studiant.com.storage.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by groupama on 11/05/2017.
 */

public class RestClient {

    /**
     * This is our main backend/server URL.
     */
    //public static String REST_API_URL = "https://loopbackstudiant.herokuapp.com/";
    //public static final String REST_API_URL = "https://fcm.googleapis.com/";
    //public static final String REST_API_URL = "http://192.168.0.12:3000";

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(new StethoInterceptor())
            .build();


    private static Retrofit.Builder retrofit = new Retrofit.Builder()
            // .baseUrl(REST_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client);


    synchronized public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return retrofit.baseUrl(baseUrl).build().create(serviceClass);
    }
}

