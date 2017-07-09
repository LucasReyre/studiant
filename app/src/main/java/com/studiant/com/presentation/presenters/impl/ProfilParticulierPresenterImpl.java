package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ConnexionFacebookInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetProfileInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertUserInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.ConnexionPresenter;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.storage.ConnexionRepository;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ProfilParticulierPresenterImpl extends AbstractPresenter implements ProfilParticulierPresenter, GetProfileInteractor.Callback,
        InsertUserInteractor.Callback{

    private View mView;
    private UserRepository mUserRepository;

    public ProfilParticulierPresenterImpl(Executor executor, MainThread mainThread,
                                          View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
        getFacebookData();
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
    public void getFacebookData() {

        GetProfileInteractor interactor = new GetProfileInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository
        );

        // run the interactor
        interactor.execute();

    }

    @Override
    public void insertProfile(User user) {

        InsertUserInteractor interactor = new InsertUserInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                PresentationModelConverter.convertToUserDomainModel(user)
        );

        interactor.execute();
    }

    @Override
    public void onProfileRetrieve(User user) {
        mView.onProfileRetrieve(user);
    }

    @Override
    public void onUserInsert(com.studiant.com.domain.model.User user) {
        mView.onUserInsert(PresentationModelConverter.convertToUserPresenterModel(user));

    }

    @Override
    public void onRetrievalFailed(String error) {

    }
}
