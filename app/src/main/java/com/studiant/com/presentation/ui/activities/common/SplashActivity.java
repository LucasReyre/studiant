package com.studiant.com.presentation.ui.activities.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.notification.MyFirebaseInstanceIDService;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.impl.SplashPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.SplashPresenter;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.presentation.ui.activities.particulier.DashboardParticulierActivity;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;

/**
 * Created by lucas on 12/04/2016.
 */
public class SplashActivity extends Activity implements SplashPresenter.View, MyFirebaseInstanceIDService.FirebaseInterface{
    Context context = this;

    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        MyFirebaseInstanceIDService.setListener(this);

        // create a presenter for this view
        mPresenter = new SplashPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }


    @Override
    public void onProfileRetrieve(User user) {
        Intent intent = new Intent();

        if (user.getTypeUser() == STATUS_ETUDIANT)
            intent = new Intent(context, DashboardEtudiantActivity.class);
        else if(user.getTypeUser() == STATUS_PARTICULIER)
            intent = new Intent(context, DashboardParticulierActivity.class);

        intent.putExtra(INTENT_USER, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRetrievalFailed() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onTokenRefresh(String token) {
        Log.d("SplashToken", "ontokenRefresh"+token);
        Log.d("SplashToken", " token : "+FirebaseInstanceId.getInstance().getToken());
    }
}