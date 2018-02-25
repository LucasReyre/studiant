package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.GetMoneyInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.Payout;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetMoneyInteractorImpl extends AbstractInteractor implements GetMoneyInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private User mUser;

    public GetMoneyInteractorImpl(Executor threadExecutor,
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
                mCallback.onMoneyRetrievalFailed("Money Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onMoneyRetrieve();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            Payout payout = mUserRepository.getMoney(PresentationModelConverter.convertToUserDomainModel(mUser));

            if (payout != null && payout.getStatus() == "CREATED")
                postMessage();
            else
                notifyError();

        } catch (Exception e) {
            e.printStackTrace();
            notifyError();
        }

        // we have retrieved our message, notify the UI on the main thread
        //postMessage(jobArrayList);
    }

}
