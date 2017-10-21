package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

import java.util.ArrayList;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetCardInteractorImpl extends AbstractInteractor implements GetCardInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private User mUser;

    public GetCardInteractorImpl(Executor threadExecutor,
                                 MainThread mainThread,
                                 Callback callback, UserRepository userRepository, User user) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mUser = user;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCardRetrievalFailed("Card Retreval Failed");
            }
        });
    }

    private void postMessage(final Card card) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onCardRetrieve();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            Card card = mUserRepository.getCardUser(PresentationModelConverter.convertToUserDomainModel(mUser));

            if (card.getId() == null)
                notifyError();
            else
                postMessage(card);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // we have retrieved our message, notify the UI on the main thread
        //postMessage(jobArrayList);
    }

}
