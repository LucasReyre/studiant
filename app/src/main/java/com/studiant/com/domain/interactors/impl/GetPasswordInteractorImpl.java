package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetPasswordInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetPasswordInteractorImpl extends AbstractInteractor implements GetPasswordInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private String mail;

    public GetPasswordInteractorImpl(Executor threadExecutor,
                                     MainThread mainThread,
                                     Callback callback, UserRepository userRepository, String mail) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        this.mail = mail;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPasswordRetrievalFailed("Card Retreval Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onPasswordRetrieve();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            mUserRepository.getPassword(mail);
            postMessage();

        } catch (Exception e) {
            notifyError();
            e.printStackTrace();
        }

        // we have retrieved our message, notify the UI on the main thread
        //postMessage(jobArrayList);
    }

}
