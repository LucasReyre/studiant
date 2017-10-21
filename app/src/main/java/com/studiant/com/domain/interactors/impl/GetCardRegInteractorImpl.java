package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardRegInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetCardRegInteractorImpl extends AbstractInteractor implements GetCardRegInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private User mUser;

    public GetCardRegInteractorImpl(Executor threadExecutor,
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
                mCallback.onCardRegRetrievalFailed("Card Retreval Failed");
            }
        });
    }

    private void postMessage(final CardReg cardReg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onCardRegRetrieve(cardReg);
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            CardReg cardReg = mUserRepository.getCardReg(PresentationModelConverter.convertToUserDomainModel(mUser));

            if (cardReg.getAccessKey() == null)
                notifyError();
            else
                postMessage(cardReg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
