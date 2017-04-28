package com.studiant.com.domain.interactors.impl;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.storage.ConnexionRepository;
import com.studiant.com.storage.impl.UserRepositoryImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetProfileInteractorImpl extends AbstractInteractor implements GetProfileInteractor {

    private GetProfileInteractor.Callback mCallback;
    private UserRepository mUserRepository;

    public GetProfileInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final User user) {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onProfileRetrieve(user);
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        final User user = mUserRepository.getConnectedProfile();

        // we have retrieved our message, notify the UI on the main thread
        postMessage(user);
    }
}
