package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.DirectPaymentInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.PayIn;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class DirectPaymentInteractorImpl extends AbstractInteractor implements DirectPaymentInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private String mMangoPayId;
    private String mAmount;

    public DirectPaymentInteractorImpl(Executor threadExecutor,
                                       MainThread mainThread,
                                       Callback callback, UserRepository userRepository, String mangoPayId, String amount) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mMangoPayId = mangoPayId;
        mAmount = amount;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDirectPaymentFailed("Card Retreval Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onDirectPaymentAccepted();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {

           PayIn payIn = mUserRepository.directPayment(mMangoPayId,mAmount);
            System.out.println("payin : "+payIn.getStatus());
            if (payIn.getStatus().equals("SUCCEEDED"))
                postMessage();
            else
                notifyError();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // we have retrieved our message, notify the UI on the main thread
        //postMessage(jobArrayList);
    }

}
