package com.studiant.com;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        // initiate Timber
        Timber.plant(new DebugTree());
    }


    public static Context getContext() {
        return context;
    }

}
