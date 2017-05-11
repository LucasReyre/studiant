package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by lucas on 12/04/2016.
 */
public class SplashActivity extends Activity {
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();


    }


}