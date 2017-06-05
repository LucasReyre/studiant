package com.studiant.com.domain.interactors.impl;

import android.content.res.Resources;
import android.util.Log;

import com.studiant.com.R;
import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;

import java.util.Calendar;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetProfileFromConnectedInteractorImpl extends AbstractInteractor implements GetProfileInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;

    public GetProfileFromConnectedInteractorImpl(Executor threadExecutor,
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
                if (user != null)
                    mCallback.onProfileRetrieve(PresentationModelConverter.convertToUserPresenterModel(user));
                else if (user==null)
                    mCallback.onRetrievalFailed("Le profil n\\'a pas été retrouvé");
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        Log.d("splash", "run");
        final User user = mUserRepository.getProfileFromConnectedUser();
        // we have retrieved our message, notify the UI on the main thread
        postMessage(user);
    }

}
