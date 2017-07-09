package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.impl.ConnexionFacebookInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.ChoosePresenter;
import com.studiant.com.presentation.presenters.interfaces.ConnexionPresenter;
import com.studiant.com.storage.ConnexionRepository;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ConnexionPresenterImpl extends AbstractPresenter implements ConnexionPresenter,
        ConnexionFacebookInteractor.Callback {

    private View mView;
    private ConnexionRepository mConnexionRepository;

    public ConnexionPresenterImpl(Executor executor, MainThread mainThread,
                                  View view, ConnexionRepository connexionRepository) {
        super(executor, mainThread);
        mView = view;
        mConnexionRepository = connexionRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
        /*ConnexionInteractor interactor = new ConnexionInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mConnexionRepository
        );

        // run the interactor
        interactor.execute();*/
    }

    public void setFacebookData(){
        mView.showProgress();

        ConnexionFacebookInteractor interactor = new ConnexionFacebookInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mConnexionRepository
        );

        // run the interactor
        interactor.execute();
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
    public void onConnexionFacebookSuccess() {

    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}
