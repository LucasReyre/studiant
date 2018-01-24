package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetMoneyInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetProfileInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertUserInteractorImpl;
import com.studiant.com.domain.interactors.impl.SaveUserInteractorImpl;
import com.studiant.com.domain.interactors.impl.UploadImageInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetMoneyInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.domain.interactors.interfaces.UploadImageInteractor;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.ProfilEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ProfilEtudiantPresenterImpl extends AbstractPresenter implements ProfilEtudiantPresenter,
        GetProfileInteractor.Callback,
        InsertUserInteractor.Callback, UploadImageInteractor.Callback, GetMoneyInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public ProfilEtudiantPresenterImpl(Executor executor, MainThread mainThread,
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
    public void onUserInsert(com.studiant.com.domain.model.User user) {

    }

    @Override
    public void onImageUpload(String image) {

    }

    @Override
    public void onProfileRetrieve(User user) {

    }

    @Override
    public void getMoney(User user) {
        GetMoneyInteractor interactor = new GetMoneyInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                user
        );

        // run the interactor
        interactor.execute();

    }

    @Override
    public void onRetrievalFailed(String error) {

    }

    @Override
    public void onMoneyRetrieve() {

    }

    @Override
    public void onMoneyRetrievalFailed(String error) {

    }
}
