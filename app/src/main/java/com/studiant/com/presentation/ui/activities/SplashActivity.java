package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.impl.SplashPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.SplashPresenter;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

/**
 * Created by lucas on 12/04/2016.
 */
public class SplashActivity extends Activity implements SplashPresenter.View{
    Context context = this;

    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create a presenter for this view
        mPresenter = new SplashPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl() {
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }


    @Override
    public void onProfileRetrieve(User user) {
        Intent intent = new Intent(context, DashboardEtudiantActivity.class);
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
}