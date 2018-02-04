package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetMoneyInteractorImpl;
import com.studiant.com.domain.interactors.impl.SaveUserInteractorImpl;
import com.studiant.com.domain.interactors.impl.UpdateUserInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetMoneyInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.domain.interactors.interfaces.UpdateUserInteractor;
import com.studiant.com.domain.interactors.interfaces.UploadImageInteractor;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.ProfilEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierDashboardPresenter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ProfilParticulierDashboardPresenterImpl extends AbstractPresenter implements ProfilParticulierDashboardPresenter,
        UpdateUserInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public ProfilParticulierDashboardPresenterImpl(Executor executor, MainThread mainThread,
                                                  View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {
        mView.showProgress();
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
    public void saveUser(User user) {
        mView.showProgress();
        UpdateUserInteractor interactor = new UpdateUserInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                PresentationModelConverter.convertToUserDomainModel(user)
        );

        // run the interactor
        interactor.execute();
    }


    @Override
    public void onUserUpdate(com.studiant.com.domain.model.User user) {
        SaveUserInteractor interactor = new SaveUserInteractorImpl(
                mExecutor,
                mMainThread,
                PresentationModelConverter.convertToUserPresenterModel(user)
        );

        interactor.execute();
        mView.hideProgress();
        mView.onUserUpdate();
    }

    @Override
    public void onUpdateFailed(String error) {
        mView.hideProgress();
        mView.onUserUpdateFailed();

    }
}
