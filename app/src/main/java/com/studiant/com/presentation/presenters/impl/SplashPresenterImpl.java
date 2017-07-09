package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetProfileFromConnectedInteractorImpl;
import com.studiant.com.domain.interactors.impl.SendNotificationInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.SendNotificationInteractor;
import com.studiant.com.domain.repository.GCMMessageRepository;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.SplashPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class SplashPresenterImpl extends AbstractPresenter implements SplashPresenter, GetProfileInteractor.Callback{

    private View mView;
    private UserRepository mUserRepository;
    private GCMMessageRepository mGcmRepository;

    public SplashPresenterImpl(Executor executor, MainThread mainThread,
                               View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
        GetProfileInteractor interactor = new GetProfileFromConnectedInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository
        );

        // run the interactor
        interactor.execute();
        Log.d("splash", "resume Presenter");

    }


    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        //mView.showError(message);
    }

    @Override
    public void onProfileRetrieve(User user) {
        Log.d("splash", "presenter onProfileRetrive");
        mView.onProfileRetrieve(user);
    }

    @Override
    public void onRetrievalFailed(String error) {
        Log.d("splash", "presenter onRetrievalFailed");
        mView.onRetrievalFailed();

    }
}
