package com.studiant.com.network;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

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
    public static final String REST_API_URL = "https://loopbackstudiant.herokuapp.com/";
//    public static final String REST_API_URL = "http://192.168.0.12:3000";


    private static Retrofit s_retrofit;

    static {

        Log.d("response", "start RestClient");
        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        s_retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T getService(Class<T> serviceClass) {
        Log.d("response", "getService");
        return s_retrofit.create(serviceClass);
    }
}
