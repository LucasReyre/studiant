package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.DirectPaymentInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetCardRegInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertJobInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.DirectPaymentInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardRegInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddCbPresenter;
import com.studiant.com.presentation.presenters.interfaces.ConfirmPaymentPresenter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ConfirmPaymentPresenterImpl extends AbstractPresenter implements ConfirmPaymentPresenter, DirectPaymentInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public ConfirmPaymentPresenterImpl(Executor executor, MainThread mainThread,
                                       View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {
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
    public void directePayment(String mangoPayId, String amount) {
        mView.showProgress();
        DirectPaymentInteractor interactor = new DirectPaymentInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                mangoPayId,
                amount
        );

        // run the interactor
        interactor.execute();

    }

    @Override
    public void onDirectPaymentAccepted() {
        mView.hideProgress();
        mView.onPaymentAccepted();
    }

    @Override
    public void onDirectPaymentFailed(String error) {
        mView.hideProgress();
        mView.onPaymentRefused();
    }
}
